package org.nanotek.opencsv;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.IdBase;
import org.nanotek.ImmutableBase;
import org.nanotek.ProcessorBase;
import org.nanotek.ValueBase;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.nanotek.opencsv.task.CsvProcessorCallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFutureTask;

import au.com.bytecode.opencsv.bean.CsvToBean;

public class CsvBaseProcessor
<T extends BaseMap<S,P,M> , 
S  extends AnyBase<S,String> , 
P   extends AnyBase<P,Integer> , 
M extends BaseBean<?,?>, 
R extends CsvResult<?,?>> 
implements ProcessorBase<R> , Base<R> , InitializingBean , ApplicationContextAware{

	private static Logger log = LoggerFactory.getLogger(CsvBaseProcessor.class);

	private int INIT_VALUE=10;
	
	@Autowired
	@Qualifier("CsvProcessorCallBack")
	public CsvProcessorCallBack<R,?> csvProcessorCallBack;
	
	ApplicationContext applicationContext;

	@Autowired
	@Qualifier(value = "serviceTaskExecutor")
    private ThreadPoolTaskExecutor serviceTaskExecutor;
	
	private AtomicInteger counter = new  AtomicInteger(INIT_VALUE);

	/**
	 * 
	 */
	private static final long serialVersionUID = -9020375809532500851L;

	private BaseParser<T,S,P,M> parser; 

	private CsvToBean<M> csvToBean;

	private CsvFileItemConcreteStrategy<T,S,P,M> mapColumnStrategy;
	

	public CsvBaseProcessor(BaseParser<T,S,P,M> parser) {
		super();
		this.parser = parser;
		csvToBean = new CsvToBean<>();
	}


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
	}	

	public BaseParser getBaseParser() {
		return parser;
	}

	public void reopenFile() throws Exception {
		mapColumnStrategy.reopen();
	}
	
	public void getNext(){
		try {
			Callable<R> call = new ResultCallable();
			ListenableFutureTask<R> ar = new ListenableFutureTask<R>(call);
			ar.addCallback(csvProcessorCallBack);
			Thread t = serviceTaskExecutor.createThread(ar);
			t.setPriority(counter.getAndUpdate(new IntUnaryOperator() {
				@Override
				public int applyAsInt(int operand) {
					if (operand == 1) {
						operand = INIT_VALUE; 
					}else
						--operand;
					return operand;
				}
			}));
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
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
		
		@SuppressWarnings("unchecked")
		public  R computeNext()  {
			Optional<R> result = Optional.empty();
			List<ValueBase<?>> next = getBaseParser().readNext();
			if (next !=null) {
				BaseBean<?,?> base = csvToBean.processLine(mapColumnStrategy.getMapColumnStrategy(), next);
//				result =  of(applicationContext.getBean(CsvResult.class, base));
				result =  ImmutableBase.newInstance(CsvResult.class , Arrays.asList(IdBase.class.cast(base)).toArray() , BaseBean.class);
			}
			return result.map(r->r).orElse(null);
		}
		
	}

	@SuppressWarnings("unchecked")
	public  Optional<R> of(CsvResult<?,?> bean) {
		return Optional.of((R)bean);
	}

}
