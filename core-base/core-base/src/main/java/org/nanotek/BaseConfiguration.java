package org.nanotek;

import java.beans.beancontext.BeanContextSupport;
import java.lang.annotation.Annotation;
import java.util.concurrent.Executor;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.Validator;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.BaseParser;
import org.nanotek.opencsv.CsvBaseProcessor;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.opencsv.file.CsvBaseConfig;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.nanotek.opencsv.task.CsvProcessorCallBack;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ResolvableType;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.PostgresDialect;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationInterceptor;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
//import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.google.gson.Gson;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import au.com.bytecode.opencsv.bean.CsvToBean;


@Configuration
@ComponentScan("org.nanotek")
//@EnableCaching(proxyTargetClass=true)
@EnableAsync(proxyTargetClass=true)
@EnableJpaRepositories(basePackages = {"org.nanotek.repository.jpa"})
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EnableSolrRepositories(basePackages = "org.nanotek.repository.solr")
//@EnableAspectJAutoProxy(proxyTargetClass = false)
public class BaseConfiguration extends AbstractJdbcConfiguration implements ApplicationContextAware{ 
    
    @Override
    public Dialect jdbcDialect(NamedParameterJdbcOperations operations) {
        return PostgresDialect.INSTANCE;
    }

	private ApplicationContext applicationContext;

	@Bean
	@Qualifier(value = "ApachePropertyUtils")
	PropertyUtils getPropertyUtils() {
		return new PropertyUtils();
	}


	@Bean
	public Jackson2ObjectMapperBuilder configureObjectMapper() {
		return new Jackson2ObjectMapperBuilder().modulesToInstall(Hibernate5Module.class);
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
	MBeanServerFactoryBean getMBeanServerFactoryBean() { 
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
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	//	@Bean
	//	public Jackson2ObjectMapperBuilder configureObjectMapper() {
	//	    return new Jackson2ObjectMapperBuilder().modulesToInstall(Arrays.array(Hibernate5Module.class));
	//	}


	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public LocalValidatorFactoryBean getLocalValidatorFactoryBean() { 
		return new LocalValidatorFactoryBean();
	}

	@Bean(name = "MethodValidationInterceptor")
	@Qualifier(value="MethodValidationInterceptor")
	MethodValidationInterceptor getMethodValidationInterceptor(@Autowired Validator validator) { 
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

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("org.nanotek");
		factory.setPersistenceUnitName("spring-core-music-brainz");
		factory.setDataSource(dataSource());
		return factory;
	}

	//
	@Bean
	public PlatformTransactionManager transactionManager(@Autowired EntityManagerFactory entityManagerFactory) { 
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory); 
		return txManager; 
	}

	@Bean(name = "serviceTaskExecutor")
	@Qualifier(value = "serviceTaskExecutor")
	public ThreadPoolTaskExecutor getServiceTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(1000);
		executor.setQueueCapacity(100000);
		executor.setThreadNamePrefix("ServiceThreadPoolExecutor");
		executor.initialize();
		return executor;
	}

	@Bean(name = "threadPoolTaskExecutor")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(100000);
		executor.setThreadNamePrefix("AsyncThreadPoolExecutor");
		executor.initialize();
		return executor;
	}

	@Bean(name="BeanContextSupport")
	@Qualifier(value="BeanContextSupport")
	public BeanContextSupport getBeanContextSupport() {
		return new BeanContextSupport();
	}

	@Bean(name="Registry")
	@Qualifier(value="Registry")
	@Primary
	public Registry<?> getRegistry(@Autowired BeanContextSupport beanContextSupport){
		return  new Registry<>(beanContextSupport).registar();
	}


//	@Bean
//	@ConfigurationProperties(value = "instrument")
//	@Qualifier(value="CsvFileItemConcreteStrategy")
//	<T extends BaseMap<S,P,M> , 
//	S  extends AnyBase<S,String> , 
//	P   extends AnyBase<P,Integer> , 
//	M extends BaseBean<?,?>>
//	CsvFileItemConcreteStrategy<T,S,P,M> getCsvFileItemConfigMappingStrategy() { 
//		return new CsvFileItemConcreteStrategy<T,S,P,M>();
//	}
//
//	@Bean(name="BaseParser")
//	@Qualifier(value="BaseParser")
//	public
//	<T extends BaseMap<S,P,M> , 
//	S  extends AnyBase<S,String> , 
//	P   extends AnyBase<P,Integer> , 
//	M extends BaseBean<?,?>>
//	BaseParser<T,S,P,M> getBaseParser(@Autowired @Qualifier("CsvFileItemConcreteStrategy")CsvFileItemConcreteStrategy<T,S,P,M> strategy) { 
//		return new BaseParser<T,S,P,M>(strategy);
//	}

//	@Bean(name = "CsvToBean")
//	@Qualifier(value="CsvToBean")
//	public <M extends BaseBean<?,?>> CsvToBean<M> getCsvToBean(){
//		return new CsvToBean<M>();
//	}
//
//	@Bean(name = "CsvBaseProcessor")
//	@Qualifier(value="CsvBaseProcessor")
//	public
//	<T extends BaseMap<S,P,M> , 
//	S  extends AnyBase<S,String> , 
//	P   extends AnyBase<P,Integer> , 
//	M extends BaseBean<?,?>, 
//	R extends CsvResult<?,?>> 
//	CsvBaseProcessor <T,S,P,M,R> getCsvBaseProcessor(
//			@Autowired @Qualifier("BaseParser") BaseParser<T,S,P,M> parser , 
//			@Autowired @Qualifier("CsvToBean")CsvToBean<M> csvToBean,
//			@Autowired @Qualifier("CsvFileItemConcreteStrategy")CsvFileItemConcreteStrategy<T,S,P,M> strategy) {
//		return new CsvBaseProcessor<T,S,P,M,R>(parser,csvToBean,strategy);
//	}
//
//	@Bean(name = "CsvProcessorCallBack")
//	@Qualifier(value = "CsvProcessorCallBack")
//	public <R extends CsvResult<?, B>,B extends BrainzBaseEntity<B>> CsvProcessorCallBack<R,B> getCsvProcessorCallBack() {
//		return new CsvProcessorCallBack<R,B>();
//	}

	@Bean(name="Reflections")
	@Qualifier(value="Reflections")
	public Reflections getReflections() {
		return new Reflections(new ConfigurationBuilder()
				.setUrls(ClasspathHelper.forPackage("org.nanotek.beans.entity"))
				.setScanners(new SubTypesScanner(), 
						new TypeAnnotationsScanner()));
	}


	@Bean
	public Gson gson() {
		return new Gson();
	}


	private String baseSolrUrl = "http://localhost:8983/solr/";
	
	@Bean
	public SolrClient solrClient() {
		HttpSolrClient.Builder builder =   new HttpSolrClient.Builder();
		return builder.withBaseSolrUrl(baseSolrUrl).build();
	}

    @Bean
    public SolrTemplate solrTemplate(@Autowired SolrClient solrClient) {
        return new SolrTemplate(solrClient);
    }
    
	@Bean
	public SolrOperations solrTemplate() {
		return new SolrTemplate(solrClient());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public String getId() {
		return applicationContext.getId();
	}

	public String[] getBeanNamesForType(ResolvableType type) {
		return applicationContext.getBeanNamesForType(type);
	}

	public String[] getBeanNamesForType(ResolvableType type, boolean includeNonSingletons, boolean allowEagerInit) {
		return applicationContext.getBeanNamesForType(type, includeNonSingletons, allowEagerInit);
	}

	public Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	public String[] getBeanNamesForType(Class<?> type) {
		return applicationContext.getBeanNamesForType(type);
	}

	public Object getBean(String name, Object... args) throws BeansException {
		return applicationContext.getBean(name, args);
	}

	public <T> T getBean(Class<T> requiredType) throws BeansException {
		return applicationContext.getBean(requiredType);
	}

	public String[] getBeanNamesForType(Class<?> type, boolean includeNonSingletons, boolean allowEagerInit) {
		return applicationContext.getBeanNamesForType(type, includeNonSingletons, allowEagerInit);
	}

	public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
		return applicationContext.getBean(requiredType, args);
	}

	public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
		return applicationContext.getBeanNamesForAnnotation(annotationType);
	}
	
	
	@Bean
	@ConfigurationProperties(value = "gendertype")
	@Qualifier(value="ArtistCsvFileItemConcreteStrategy")
	CsvBaseConfig getArtistsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "area")
	@Qualifier(value="AreaTypeCsvFileItemConcreteStrategy")
	CsvBaseConfig getAreaCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}

	@Bean
	@ConfigurationProperties(value = "areatype")
	@Qualifier(value="AreaTypeCsvFileItemConcreteStrategy")
	CsvBaseConfig getAreaTypeCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "artistaliastype")
	@Qualifier(value="ArtisAliasTypeCsvFileItemConcreteStrategy")
	CsvBaseConfig getArtisAliasTypeCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "artistalias")
	@Qualifier(value="ArtisAliasCsvFileItemConcreteStrategy")
	CsvBaseConfig getArtisAliasCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "artistcredit")
	@Qualifier(value="ArtisCreditCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getArtisCreditCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "artistcreditedname")
	@Qualifier(value="ArtisCreditNameCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getArtisCreditNameCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "releasepackaging")
	@Qualifier(value="ReleasePackagingCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getReleasePackagingCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "releasegroupprimarytype")
	@Qualifier(value="ReleaseGroupPrimaryTypeCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getReleaseGroupPrimaryTypeCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "labeltype")
	@Qualifier(value="LabelTypeCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getLabelTypeCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "instrumenttype")
	@Qualifier(value="InstrumentTypeCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getInstrumentTypeCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "gendertype")
	@Qualifier(value="GenderTypeCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getGenderTypeCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "artisttype")
	@Qualifier(value="ArtistTypeCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getArtistTypeCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "recordingaliastype")
	@Qualifier(value="RecordAliasTypeCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getRecordAliasTypeCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "releasealiastype")
	@Qualifier(value="ReleaseAliasTypeCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getReleaseAliasTypeCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "worktype")
	@Qualifier(value="WorkTypeCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getWorkTypeCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "instrument")
	@Qualifier(value="InstrumentCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getInstrumentCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	
	@Bean
	@ConfigurationProperties(value = "mediumbean")
	@Qualifier(value="MediumCsvFileItemConfigMappingStrategy")
	CsvBaseConfig getMediumCsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "recordingbean")
	@Qualifier(value="RecordingsvFileItemConfigMappingStrategy")
	CsvBaseConfig getRecordingsvFileItemConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "language")
	@Qualifier(value="LanguageConfigMappingStrategy")
	CsvBaseConfig getLanguageConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "isrcbean")
	@Qualifier(value="IsrcbeanConfigMappingStrategy")
	CsvBaseConfig getIsrcbeanConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "labelbean")
	@Qualifier(value="LabelBeanConfigMappingStrategy")
	CsvBaseConfig getLabelBeanConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "releasegroup")
	@Qualifier(value="ReleaseGroupConfigMappingStrategy")
	CsvBaseConfig getReleaseGroupConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	
	@Bean
	@ConfigurationProperties(value = "releasestatus")
	@Qualifier(value="ReleaseStatusGroupConfigMappingStrategy")
	CsvBaseConfig getReleaseStatusGroupConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "releasebean")
	@Qualifier(value="ReleaseConfigMappingStrategy")
	CsvBaseConfig getReleaseConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "releaselabelbean")
	@Qualifier(value="ReleaseLabelConfigMappingStrategy")
	CsvBaseConfig getReleaseLabelConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "workbean")
	@Qualifier(value="WorkConfigMappingStrategy")
	CsvBaseConfig getWorkConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "releasealiasbean")
	@Qualifier(value="ReleaseAliasConfigMappingStrategy")
	CsvBaseConfig getReleaseAliasConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
	
	@Bean
	@ConfigurationProperties(value = "trackbean")
	@Qualifier(value="TrackConfigMappingStrategy")
	CsvBaseConfig getTrackConfigMappingStrategy() { 
		return new CsvBaseConfig();
	}
}
