package org.nanotek.opencsv;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Validator;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.collections.BaseMap;
import org.nanotek.config.CsvFileConfigurations;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.nanotek.opencsv.task.CsvProcessorCallBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//@Configuration
//@EnableConfigurationProperties(CsvFileConfigurations.class)
public class CsvBaseConfiguration2 { // Renamed from BaseConfiguration

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public <T extends BaseMap<S,P,M> ,
            S  extends AnyBase<S,String> ,
            P   extends AnyBase<P,Integer> ,
            M extends BaseBean<?,?>> Map<String, CsvFileItemConcreteStrategy2<T,S,P,M>> allCsvFileItemConcreteStrategies(
            CsvFileConfigurations<T,S,P,M> csvFileConfigurations) {
        csvFileConfigurations
                .getCsvConfigs()
                .values()
                .forEach(strategy -> {
                    strategy
                            .afterPropertiesSet();
                });
        return csvFileConfigurations.getCsvConfigs();
    }

    // --- Singleton Map of BaseParser instances ---
    @Bean(name = "BaseParserMap")
    public <T extends BaseMap<S,P,M> ,
            S  extends AnyBase<S,String> ,
            P   extends AnyBase<P,Integer> ,
            M extends BaseBean<?,?>> Map<String, BaseParser<T,S,P,M>> getBaseParserMap(
            Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> allCsvFileItemConcreteStrategies) {

        Map<String, BaseParser<T,S,P,M>> parserMap = new HashMap<>();

        allCsvFileItemConcreteStrategies.forEach((configName, strategy) -> {
            BaseParser<T,S,P,M> parser = new BaseParser<>(strategy);
            try {
                parser.afterPropertiesSet();
                // Manually set ApplicationContext if BaseParser is ApplicationContextAware
                if (parser instanceof org.springframework.context.ApplicationContextAware) {
                    ((org.springframework.context.ApplicationContextAware) parser).setApplicationContext(applicationContext);
                }
            } catch (Exception e) {
                System.err.println("Error initializing BaseParser for " + configName + ": " + e.getMessage());
            }
            parserMap.put(configName, parser);
        });
        System.out.println("CsvBaseConfiguration: Initialized " + parserMap.size() + " BaseParser instances in BaseParserMap."); // Updated log
        return parserMap;
    }

    // CsvToBean remains prototype
    @Bean(name = "CsvToBean")
    @Scope("prototype")
    public <M extends BaseBean<?,?>> au.com.bytecode.opencsv.bean.CsvToBean<M> getCsvToBean(){
        return new au.com.bytecode.opencsv.bean.CsvToBean<>();
    }

    // CsvProcessorCallBack remains prototype
    @Bean(name = "CsvProcessorCallBack")
    @Scope("prototype")
    public <R extends CsvResult<?, B>,B extends BrainzBaseEntity<B>> CsvProcessorCallBack<R,B> getCsvProcessorCallBack() {
        // CsvProcessorCallBack is ApplicationContextAware, so set context manually if needed
        CsvProcessorCallBack<R,B> callBack = new CsvProcessorCallBack<>();
        if (callBack instanceof org.springframework.context.ApplicationContextAware) {
            ((org.springframework.context.ApplicationContextAware) callBack).setApplicationContext(applicationContext);
        }
        return callBack;
    }

    // --- Singleton Map of CsvBaseProcessor instances ---
    @Bean(name = "CsvBaseProcessorMap")
    public <T extends BaseMap<S,P,M> ,
            S  extends AnyBase<S,String> ,
            P   extends AnyBase<P,Integer> ,
            M extends BaseBean<?,?>,
            R extends CsvResult<?,B> , B extends BrainzBaseEntity<B>> Map<String, CsvBaseProcessor<T,S,P,M,R>> getCsvBaseProcessorMap(
            Map<String, BaseParser<T,S,P,M>> baseParserMap,
            Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> allCsvFileItemConcreteStrategies) {

        Map<String, CsvBaseProcessor<T,S,P,M,R>> processorMap = new HashMap<>();

        allCsvFileItemConcreteStrategies.forEach((configName, strategy) -> {
            BaseParser<T,S,P,M> parser = baseParserMap.get(configName);

            if (parser == null) {
                System.err.println("Warning: No BaseParser found for config: " + configName + ". Skipping CsvBaseProcessor creation.");
                return;
            }

            au.com.bytecode.opencsv.bean.CsvToBean<M> csvToBean = getCsvToBean();
            CsvProcessorCallBack<R, B> callBack = getCsvProcessorCallBack();

            CsvBaseProcessor<T,S,P,M,R> processor = new CsvBaseProcessor<>(parser, csvToBean, strategy);

            processor.csvProcessorCallBack = callBack;

            if (processor instanceof org.springframework.context.ApplicationContextAware) {
                ((org.springframework.context.ApplicationContextAware) processor).setApplicationContext(applicationContext);
            }

            processorMap.put(configName, processor);
        });
        System.out.println("CsvBaseConfiguration: Initialized " + processorMap.size() + " CsvBaseProcessor instances in CsvBaseProcessorMap."); // Updated log
        return processorMap;
    }

    @Bean(name = "serviceTaskExecutor3")
    public ThreadPoolTaskExecutor serviceTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("CsvProcessor-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "validator")
    public Validator validator() {
        return javax.validation.Validation.buildDefaultValidatorFactory().getValidator();
    }
}