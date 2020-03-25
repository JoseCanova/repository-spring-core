package org.nanotek;

import java.beans.beancontext.BeanContextSupport;
import java.util.concurrent.Executor;

import javax.validation.Validator;

import org.apache.commons.beanutils.PropertyUtils;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.BaseParser;
import org.nanotek.opencsv.CsvBaseProcessor;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jmx.support.MBeanServerFactoryBean;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationInterceptor;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

//import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.google.gson.Gson;

import au.com.bytecode.opencsv.bean.CsvToBean;

@Configuration
@ComponentScan("org.nanotek")
@EnableCaching
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class BaseConfiguration {

	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	@Qualifier(value = "ApachePropertyUtils")
	PropertyUtils createApachePropertyUtils() {
		return new PropertyUtils();
	}
	
//	@Bean
//	ManagementService ehCacheJmx(
//							@Autowired @Qualifier("ehCacheManager")CacheManager cacheManager , 
//							@Autowired @Qualifier("mBeanServer") MBeanServer mBeanServer ) { 
//		ManagementService service =  new ManagementService(cacheManager , mBeanServer , true , true , true,true);
//		service.init();
//		return service;
//	}
	
	@Bean
	@Qualifier(value="mBeanServer")
	MBeanServerFactoryBean mBeanServer() { 
		return new  MBeanServerFactoryBean();
	}
	
//	@Bean
//	@Qualifier(value="ehCacheManager")
//	EhCacheManagerFactoryBean ehCacheFactoryBean() { 
//		EhCacheManagerFactoryBean eh =  new EhCacheManagerFactoryBean();
//		eh.setShared(true);
//		return eh;
//	}
//		
//	@Bean
//	@Primary
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public HikariConfig hikariConfig() {
//		return new HikariConfig();
//	}
	
//	@Bean
//	public Jackson2ObjectMapperBuilder configureObjectMapper() {
//	    return new Jackson2ObjectMapperBuilder().modulesToInstall(Arrays.array(Hibernate5Module.class));
//	}

	
//	@Bean
//	public DataSource dataSource() {
//		return new HikariDataSource(hikariConfig());
//	}

	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean() { 
		return new LocalValidatorFactoryBean();
	}
	
	@Bean 
	public Validator validator()
	{ 
		return validatorFactoryBean().getValidator();
	}
	
	@Bean(name = "MethodValidationInterceptor")
	@Qualifier(value="MethodValidationInterceptor")
	MethodValidationInterceptor methodValidationInterceptor(@Autowired Validator validator) { 
		return new MethodValidationInterceptor(validator);
	}
	
    @Bean
    public MethodValidationPostProcessor getMethodValidationPostProcessor(@Autowired LocalValidatorFactoryBean validatorFactoryBean){
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
         processor.setValidator(validatorFactoryBean.getValidator());
         processor.setValidatorFactory(validatorFactoryBean);
         processor.setBeanFactory(applicationContext);
         return processor;
     }
	
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(true);
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setPackagesToScan("org.nanotek");
//		factory.setPersistenceUnitName("spring-core-music-brainz");
//		factory.setDataSource(dataSource());
//		return factory;
//	}

//	@Bean
//	public PlatformTransactionManager transactionManager(@Autowired EntityManagerFactory entityManagerFactory) { 
//		JpaTransactionManager txManager = new JpaTransactionManager();
//		txManager.setEntityManagerFactory(entityManagerFactory); 
//		return txManager; 
//	}

	@Bean(name = "serviceTaskExecutor")
	@Qualifier(value = "serviceTaskExecutor")
	public ThreadPoolTaskExecutor getServiceTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(1000000);
		executor.setThreadNamePrefix("ServiceThreadPoolExecutor");
		executor.initialize();
		return executor;
	}

	@Bean(name = "threadPoolTaskExecutor")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(100000);
		executor.setThreadNamePrefix("AsyncThreadPoolExecutor");
		executor.initialize();
		return executor;
	}

	@Bean
	public Registry<?> getRegistry(){
		Registry<?> r = new Registry<>(new BeanContextSupport());
		return r;
	}
	
	
	@Bean
	@ConfigurationProperties(value = "config")
	@Qualifier(value="CsvFileItemConcreteStrategy")
	<T extends BaseMap<S,P,M> , 
	S  extends AnyBase<S,String> , 
	P   extends AnyBase<P,Integer> , 
	M extends BaseBean<?,?>>
	CsvFileItemConcreteStrategy<T,S,P,M> getCsvFileItemConfigMappingStrategy() { 
		return new CsvFileItemConcreteStrategy<T,S,P,M>();
	}
	
	@Bean(name="BaseParser")
	@Qualifier(value="BaseParser")
	public
	<T extends BaseMap<S,P,M> , 
	S  extends AnyBase<S,String> , 
	P   extends AnyBase<P,Integer> , 
	M extends BaseBean<?,?>>
	 BaseParser<T,S,P,M> getBaseParser(@Autowired @Qualifier("CsvFileItemConcreteStrategy")CsvFileItemConcreteStrategy<T,S,P,M> strategy) { 
		return new BaseParser<T,S,P,M>(strategy);
	}
	
	@Bean(name = "CsvToBean")
	@Qualifier(value="CsvToBean")
	public <M extends BaseBean<?,?>> CsvToBean<M> getCsvToBean(){
		return new CsvToBean<M>();
	}
	
	@Bean(name = "CsvBaseProcessor")
	@Qualifier(value="CsvBaseProcessor")
	public
	<T extends BaseMap<S,P,M> , 
	S  extends AnyBase<S,String> , 
	P   extends AnyBase<P,Integer> , 
	M extends BaseBean<?,?>, 
	R extends Result<?,?>> 
	CsvBaseProcessor <T,S,P,M,R> getCsvBaseProcessor(
			@Autowired @Qualifier("BaseParser") BaseParser<T,S,P,M> parser , 
			@Autowired @Qualifier("CsvToBean")CsvToBean<M> csvToBean,
			@Autowired @Qualifier("CsvFileItemConcreteStrategy")CsvFileItemConcreteStrategy<T,S,P,M> strategy) {
		return new CsvBaseProcessor<T,S,P,M,R>(parser,csvToBean,strategy);
	}
	
	
	@Bean
	public Gson gson() {
		return new Gson();
	}

}
