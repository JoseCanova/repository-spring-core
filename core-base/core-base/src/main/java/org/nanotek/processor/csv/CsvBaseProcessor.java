package org.nanotek.processor.csv;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseException;
import org.nanotek.ImmutableBase;
import org.nanotek.Result;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.processor.ProcessorBase;
import org.nanotek.service.parser.BaseMapParser;

import au.com.bytecode.opencsv.bean.CsvToBean;

public class CsvBaseProcessor<I extends BaseMapColumnStrategy<?, ?>, P extends BaseMapParser<I,?>, R extends Result<?,?>> 
implements ProcessorBase<I,P,R>{

	private P parser; 
	
	private CsvToBean<?> csvToBean;
	
	public CsvBaseProcessor(P parser, CsvToBean<?> csvToBean) {
		super();
		this.parser = parser;
		this.csvToBean = csvToBean;
	}

	CsvToBean<?> getCsvToBean(){ 
		return csvToBean;
	}

	public P getBaseParser() {
		return parser;
	}
	
    public void reopenFile() throws Exception {
         getBaseParser().reopen();
    }

    public R next(){
    	return computeNext().orElseThrow(BaseException::new);
    }
    
    private Optional<R> computeNext()  {
    	BaseMapColumnStrategy <? , ? > m = parser.getBaseMapColumnStrategy();
    	Base<?> base = Base.newInstance(m.getType()).get();
			getBaseParser().readNext().ifPresent(array ->{
			m.getBaseMap().entrySet().forEach((e)->{
				computePropertyValue(e,array,base,m);}
					);
			});
		return ImmutableBase.newInstance(Result.class , Arrays.asList(base).toArray() , base.getClass());
	}

	private void computePropertyValue(Entry<String, Integer> e, String[] instanceArray, Base<?> base , BaseMapColumnStrategy <? , ? > m)  {
		try { 
			Optional.ofNullable(m.findDescriptor(e.getValue())).ifPresent(d ->{
			String value = instanceArray[e.getValue()];
			Object obj = convertPropertyValue(value,d);
			invokeWriteMethod(d , base,obj);
		});
		} catch (Exception e1) {
			throw new BaseException(e1);
		}
	}

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
    		Optional.of(bean).orElse(Base.NULL_VALUE()).ifPresent(value -> list.add(value));
    		i++;
    		if(bean.isEmpty()) break;
    	}
    	return list;
    }	
	
}
