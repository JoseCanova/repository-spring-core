package org.nanotek.opencsv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.nanotek.AnyBase;
import org.nanotek.BaseException;
import org.nanotek.ImmutableBase;
import org.nanotek.Result;
import org.nanotek.ValueBase;
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
	
	
	
	private CsvToBean<M> csvToBean;
	
	
	private CsvFileItemConcreteStrategy<T,S,P,M> mapColumnStrategy;
	
	public CsvBaseProcessor(BaseParser<T,S,P,M> parser) {
		super();
		this.parser = parser;
		csvToBean = new CsvToBean<>();
//		this.csvToBean = csvToBean;
	}

//	CsvToBean<?> getCsvToBean(){ <T,S,P,M>
//		return csvToBean;
//	}

	public CsvBaseProcessor(BaseParser<T, S, P, M> parser2, CsvToBean<M> csvToBean2) {
		super();
		this.parser = parser2;
		csvToBean = csvToBean2;
	}

	
	public CsvBaseProcessor(BaseParser<T, S, P, M> parser2, 
							CsvToBean<M> csvToBean2,
							CsvFileItemConcreteStrategy<T,S,P,M> mapColumnStrategy2) {
		super();
		this.parser = parser2;
		csvToBean = csvToBean2;
		this.mapColumnStrategy = mapColumnStrategy2;
	}

	
	public BaseParser getBaseParser() {
		return parser;
	}
	
    public void reopenFile() throws Exception {
    	mapColumnStrategy.reopen();
    }

    public R next(){
    	return computeNext().orElseThrow(BaseException::new);
    }
    
    @SuppressWarnings("unchecked")
	private Optional<R> computeNext()  {
    	try { 
			List<ValueBase<?>> next = getBaseParser().readNext();
			BaseBean<?,?> base = csvToBean.processLine(mapColumnStrategy.getMapColumnStrategy(), next);
			return ImmutableBase.newInstance(CsvResult.class , Arrays.asList(base).toArray() , base.getClass());
    	}catch (Exception ex) { 
    		throw new BaseException(ex);
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
