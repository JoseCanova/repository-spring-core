package org.nanotek.opencsv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.IdBase;
import org.nanotek.ImmutableBase;
import org.nanotek.Registry;
import org.nanotek.ValueBase;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.nanotek.opencsv.task.CsvProcessorCallBack;
import org.nanotek.processor.ProcessorBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFutureTask;

import au.com.bytecode.opencsv.bean.CsvToBean;

public class CsvBaseProcessor
<T extends BaseMap<S,P,M> , 
S  extends AnyBase<S,String> , 
P   extends AnyBase<P,Integer> , 
M extends BaseBean<?,?>, 
R extends CsvResult<?,?>> 
implements ProcessorBase<R> , Base<R> , InitializingBean , Runnable{

	private static Logger log = LoggerFactory.getLogger(CsvBaseProcessor.class);

	@Autowired
	@Qualifier("CsvProcessorCallBack")
	public CsvProcessorCallBack<R> csvProcessorCallBack;

	@Autowired
	@Qualifier(value = "serviceTaskExecutor")
    private ThreadPoolTaskExecutor serviceTaskExecutor;

	/**
	 * 
	 */
	private static final long serialVersionUID = -9020375809532500851L;

	@Autowired
	private Registry<R> registry;

	@Autowired
	private CsvResultNextEventListener<?> csvResultNextEventListener;

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

	@Override
	public void afterPropertiesSet() throws Exception {
		if (!registry.add(Base.class.cast(this)))
			throw new BaseException();
		registry.addPropertyChangeListener("next", csvResultNextEventListener);
	}	

	public BaseParser getBaseParser() {
		return parser;
	}

	public void reopenFile() throws Exception {
		mapColumnStrategy.reopen();
	}

	@Async("threadPoolTaskExecutor")
	public void run() { 
		Callable<R> call = new ResultCallable();
    	ListenableFutureTask<R> ar = new ListenableFutureTask<R>(call);
		ar.addCallback(csvProcessorCallBack);
//		try {
//			l.get();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new BaseException (e);
//		}
	} 

	@Async("threadPoolTaskExecutor")
	public R getNext(){
		R result = null;
		     	Callable<R> call = new ResultCallable();
		    	ListenableFutureTask<R> ar = new ListenableFutureTask<R>(call);
		    	ar.addCallback(csvProcessorCallBack);
		    	serviceTaskExecutor.createThread(ar).start();
		    	try {
		    		Thread.currentThread().join(1);
					result = ar.get();
				} catch (Exception e) {
					e.printStackTrace();
					throw new BaseException(e);
				}
		return result;
	}

	public  R computeNext()  {
		Optional<R> result = Optional.empty();
		List<ValueBase<?>> next = getBaseParser().readNext();
		if (next !=null) {
			BaseBean<?,?> base = csvToBean.processLine(mapColumnStrategy.getMapColumnStrategy(), next);
			result =  ImmutableBase.newInstance(CsvResult.class , Arrays.asList(IdBase.class.cast(base)).toArray() , BaseBean.class);
			//				log.debug(result.get().withUUID().toString());
			registry.firePropertyChange("next", Optional.empty(), result);
		}else { 
			throw new BaseException("Finished");
		}
		return result.get();
	}

	public List<R> load(Long count) {
		List<R> list = new  ArrayList<>();
		//    	int i = 0;
		//    	while (i < count) {
		//    		Optional<R> bean = computeNext();
		//    		bean.ifPresent(value -> list.add(value));
		//    		i++;
		//    		if(bean.isEmpty()) break;
		//    	}
		return list;
	}

	@Override
	public int compareTo(R arg0) {
		return 0;
	} 

	private class ResultCallable implements Callable<R> {

		public ResultCallable(){
		}
		
		@Override
		public R call() {
			return computeNext();
		}

		public  R computeNext()  {
			Optional<R> result = Optional.empty();
			List<ValueBase<?>> next = getBaseParser().readNext();
			if (next !=null) {
				BaseBean<?,?> base = csvToBean.processLine(mapColumnStrategy.getMapColumnStrategy(), next);
				result =  ImmutableBase.newInstance(CsvResult.class , Arrays.asList(IdBase.class.cast(base)).toArray() , BaseBean.class);
				//				log.debug(result.get().withUUID().toString());
				registry.firePropertyChange("next", Optional.empty(), result);
			}else { 
				throw new BaseException("Finished");
			}
			return result.get();
		}
		
	}

}
