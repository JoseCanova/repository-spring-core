package org.nanotek;

import java.beans.EventHandler;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

import org.nanotek.beans.csv.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.CsvBaseProcessor;
import org.nanotek.opencsv.CsvResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
//import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.integration.annotation.IntegrationComponentScan;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.config.EnableIntegrationManagement;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@EnableIntegration
//@IntegrationComponentScan
//@EnableIntegrationManagement
@EnableAutoConfiguration(exclude={RabbitAutoConfiguration.class})
//@EnableJdbcRepositories(basePackages = {"org.nanotek.repository.jdbc"})
//@EnableJpaRepositories(basePackages = {"org.nanotek.repository.jpa"})
public class App 	<T extends BaseMap<S,P,M> , 
S  extends AnyBase<S,String> , 
P   extends AnyBase<P,Integer> , 
M extends BaseBean<?,?>, 
R extends Result<?,?>,
K extends BaseBean<K,ID>,
ID extends BaseEntity<?,?>>  extends 
SpringApplication 
implements ApplicationContextAware ,  
SpringApplicationRunListener , 
ApplicationRunner{

	private ApplicationContext context;
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	public App() {
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public  void running(ConfigurableApplicationContext context) {
		System.out.println("RUNNING");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		CsvBaseProcessor <T,S,P,M,CsvResult<?,?>> processor = (CsvBaseProcessor<T, S, P, M, CsvResult<?,?>>) context.getBean("CsvBaseProcessor");
		
		CsvResult<?,?> result ; 
		do {
			result =  processor.getNext();
			Optional.ofNullable(result).ifPresent(r -> log.debug(r.withUUID().toString()));
		}while(Optional.ofNullable(result).isPresent());
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	
}
