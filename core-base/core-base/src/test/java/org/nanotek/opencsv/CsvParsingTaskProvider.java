package org.nanotek.opencsv;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.IdBase;
import org.nanotek.ImmutableBase;
// import org.nanotek.ProcessorBase; // No longer implementing ProcessorBase
import org.nanotek.ValueBase;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.task.CsvLoggerProcessorCallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
// Removed: import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFutureTask;

import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.MappingStrategy;

/**
 * A provider class responsible for generating `ListenableFutureTask` instances
 * for all configured CSV parsing strategies. This class encapsulates the creation
 * of individual parsing tasks, but delegates their submission to an external executor.
 * It is no longer directly involved in the 'processing' or 'submission' aspects of tasks.
 */
@Service
public class CsvParsingTaskProvider
<T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>,
R extends CsvResult<?,?>>
implements Base<R> ,  ApplicationContextAware { // Removed ProcessorBase<R>

	private Logger log = LoggerFactory.getLogger(CsvParsingTaskProvider.class); // Updated Logger name

	private int INIT_VALUE=10; // This field's purpose is not directly evident in current methods.

	@Autowired
//	@Qualifier("CsvProcessorCallBack") // Keeping the qualifier as it was in the original snippet.
	public CsvLoggerProcessorCallBack<R,?> csvProcessorCallBack;

	ApplicationContext applicationContext;

	// Removed: @Autowired ThreadPoolTaskExecutor serviceTaskExecutor; // No longer needed here

	@Autowired
	private CsvBaseParserConfigurations<T,S,P,M> csvParserConfigurations;

	private String configuredParserName; // This field is still present and checked in afterPropertiesSet()

	public void setConfiguredParserName(String configuredParserName) {
		this.configuredParserName = configuredParserName;
	}

	private AtomicInteger counter = new AtomicInteger(INIT_VALUE); // This field's purpose is not directly evident in current methods.

	/**
	 *
	 */
	private static final long serialVersionUID = -9020375809532500851L;


	public CsvParsingTaskProvider() { // Updated constructor name if it was `CsvBaseParserProcessor`
		super();
	}

	public CsvParsingTaskProvider(CsvToBean<M> csvToBean) { // Updated constructor name
		super();
	}

	/**
	 * Generates a Map of `ListenableFutureTask` instances for ALL configured CSV parsing strategies.
	 *
	 * <p>Each entry in the map corresponds to a parser name (key) and a
	 * `ListenableFutureTask` (value) that, when submitted and executed, will process
	 * the next available record for that specific parser.
	 *
	 * <p>Crucially, this method *does not* submit the tasks to any executor.
	 * The responsibility for submitting these tasks now lies with the caller of this method.
	 *
	 * @return A Map where keys are parser names (String) and values are
	 * `ListenableFutureTask` instances, ready for submission.
	 */
	public Map<String , ListenableFutureTask<R>> getListenableFutureTask(){
		
		return csvParserConfigurations
				.getCsvStrategies() // Retrieves a Map of parser names to CsvBaseParser instances
				.entrySet()
				.stream()
				.map(ks ->{ // ks is an Entry<String, CsvBaseParser>
					
					final CsvBaseParser<T,S,P,M> taskParser = ks.getValue();
					final Callable<R> call = new ResultCallable(taskParser);
					final ListenableFutureTask<R> ar = new ListenableFutureTask<R>(call);
					ar.addCallback(csvProcessorCallBack); // Add the common callback to each future
					return Map.entry(ks.getKey(), ar);
				}).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		
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
				CsvToBean<M> bean = new CsvToBean<>();
				final M base = bean.processLine(mapStrategy, next);
				log.debug(base.getId().toString());
				result =  ImmutableBase.newInstance(CsvResult.class , Arrays.asList(IdBase.class.cast(base),applicationContext).toArray() , BaseBean.class,ApplicationContext.class);
			} else {
				log.debug("Result is Null, stopping file processing " + new Date());
			}
			return result.map(r->r).orElse(null);
		}

	}
}