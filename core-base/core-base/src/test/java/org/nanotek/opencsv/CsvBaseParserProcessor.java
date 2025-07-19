package org.nanotek.opencsv;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.IdBase;
import org.nanotek.ImmutableBase;
import org.nanotek.ProcessorBase;
import org.nanotek.ValueBase;
import org.nanotek.collections.BaseMap;
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
import au.com.bytecode.opencsv.bean.MappingStrategy;

public class CsvBaseParserProcessor
<T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>,
R extends CsvResult<?,?>>
implements ProcessorBase<R> , Base<R> , InitializingBean , ApplicationContextAware {

	private Logger log = LoggerFactory.getLogger(CsvBaseParserProcessor.class);

	private int INIT_VALUE=10;

	@Autowired
	@Qualifier("CsvProcessorCallBack")
	public CsvProcessorCallBack<R,?> csvProcessorCallBack;

	ApplicationContext applicationContext;

	@Autowired
	@Qualifier(value = "serviceTaskExecutor")
    private ThreadPoolTaskExecutor serviceTaskExecutor;

	@Autowired
	private CsvBaseParserConfigurations<T,S,P,M> csvParserConfigurations;

	private String configuredParserName;

	public void setConfiguredParserName(String configuredParserName) {
		this.configuredParserName = configuredParserName;
	}

	private AtomicInteger counter = new AtomicInteger(INIT_VALUE);

	/**
	 *
	 */
	private static final long serialVersionUID = -9020375809532500851L;

	private CsvToBean<M> csvToBean;


	public CsvBaseParserProcessor() {
		super();
		this.csvToBean = new CsvToBean<>();
	}

	public CsvBaseParserProcessor(CsvToBean<M> csvToBean) {
		super();
		this.csvToBean = csvToBean;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (configuredParserName == null || configuredParserName.isEmpty()) {
			throw new IllegalArgumentException("configuredParserName must be set for CsvBaseParserProcessor to identify which type of parser to create for tasks.");
		}

		if (this.csvToBean == null) {
			this.csvToBean = new CsvToBean<>();
		}
	}

	public ListenableFutureTask<R> getNext(){
		final CsvBaseParser<T,S,P,M> taskParser = csvParserConfigurations.getStrategy(configuredParserName)
			.orElseThrow(() -> new IllegalStateException("No CsvBaseParser configuration found for name: " + configuredParserName + ". Cannot create task parser."));

		final Callable<R> call = new ResultCallable(taskParser);
		final ListenableFutureTask<R> ar = new ListenableFutureTask<R>(call);
		try {
			ar.addCallback(csvProcessorCallBack);
			serviceTaskExecutor.submit(ar);
		} catch (Exception e) {
			log.error("Error submitting processor task: " + e.getMessage(), e);
		}
		return ar;
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

		private final CsvBaseParser<T,S,P,M> parser;

		public ResultCallable(CsvBaseParser<T,S,P,M> parser){
			this.parser = parser;
		}

		@Override
		public R call() {
			return computeNext();
		}

		@SuppressWarnings("unchecked")
		public R computeNext() {
			Optional<R> result = Optional.empty();
			
			if (parser == null) {
				log.error("Task parser is null, cannot compute next. ResultCallable not properly initialized.");
				return null;
			}
			
			final List<ValueBase<?>> next = (List<ValueBase<?>>) parser.readNext();
			
			if (next !=null) {
				log.debug("next line " + next.toString());
				MappingStrategy<M> mapStrategy = parser.getBaseMapColumnStrategy().getMapColumnStrategy();
				final M base = csvToBean.processLine(mapStrategy, next);
				log.debug(base.getId().toString());
				// Line 151: This line is kept exactly as provided, as per your instruction.
				result =  ImmutableBase.newInstance(CsvResult.class , Arrays.asList(IdBase.class.cast(base),applicationContext).toArray() , BaseBean.class,ApplicationContext.class);
			} else {
				log.debug("Result is Null, stopping file processing " + new Date());
			}
			return result.map(r->r).orElse(null);
		}

	}
}