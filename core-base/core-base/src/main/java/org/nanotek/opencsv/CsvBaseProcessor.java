package org.nanotek.opencsv;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseException;
import org.nanotek.ImmutableBase;
import org.nanotek.Result;
import org.nanotek.StringPositionBase;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.nanotek.processor.ProcessorBase;

import au.com.bytecode.opencsv.bean.CsvToBean;

public class CsvBaseProcessor
<T extends BaseMap<S,P,M> , 
S  extends AnyBase<S,String> , 
P   extends AnyBase<P,Integer> , 
M extends BaseBean<?,?>, 
R extends Result<?,?>> 
implements ProcessorBase<R>{

	private BaseParser<T,S,P,M> parser; 
	
//	private CsvToBean<?> csvToBean;
	
	private CsvFileItemConcreteStrategy<T,S,P,M> mapColumnStrategy;
	
	public CsvBaseProcessor(BaseParser<T,S,P,M> parser) {
		super();
		this.parser = parser;
//		this.csvToBean = csvToBean;
	}

//	CsvToBean<?> getCsvToBean(){ <T,S,P,M>
//		return csvToBean;
//	}

	public BaseParser getBaseParser() {
		return parser;
	}
	
    public void reopenFile() throws Exception {
    	mapColumnStrategy.reopen();
    }

    public R next(){
    	return computeNext().orElseThrow(BaseException::new);
    }
    
    
    //TODO: review the unchecked warning.
    @SuppressWarnings("unchecked")
	private Optional<R> computeNext()  {
    	BaseBean<?,?> base = Base.newInstance(mapColumnStrategy.getType()).get();
			List<StringPositionBase<?>> next = getBaseParser().readNext();
			next.forEach(sb -> {
				PropertyDescriptor desc  = mapColumnStrategy.findDescriptor(sb.getPosition());
				String value = sb.getId();
				Object obj = convertPropertyValue(value,desc);
				invokeWriteMethod(desc , base,obj);
			});
		return ImmutableBase.newInstance(CsvResult.class , Arrays.asList(base).toArray() , base.getClass());
	}

//	private void computeProperty1Value(Entry<String, Integer> e, String[] instanceArray, Base<?> base , MapColumnStrategy <? , ? , ?> m)  {
//		try { 
//			Optional.ofNullable(m.findDescriptor(e.getValue())).ifPresent(d ->{
//			String value = instanceArray[e.getValue()];
//			Object obj = convertPropertyValue(value,d);
//			invokeWriteMethod(d , base,obj);
//		});
//		} catch (Exception e1) {
//			throw new BaseException(e1);
//		}
//	}

	private void invokeWriteMethod(PropertyDescriptor d, Base<?> base, Object obj) {
		try {
			d.getWriteMethod().invoke(base, obj);
		} catch (Exception e1) {
			throw new BaseException(e1);
		}
	}

	private Object convertPropertyValue(String value, PropertyDescriptor d) {
		try {
				return csvToBean.convertValue(value, d);
		} catch (Exception e1) {
			throw new BaseException(e1);
		}
	}

	public List<R> load(Long count) {
    	List<R> list = new  ArrayList<>();
    	int i = 0;
    	while (i < count) {
    		Optional<R> bean = computeNext();
    		bean.ifPresent(value -> list.add(value));
    		i++;
    		if(bean.isEmpty()) break;
    	}
    	return list;
    }	
	
}
