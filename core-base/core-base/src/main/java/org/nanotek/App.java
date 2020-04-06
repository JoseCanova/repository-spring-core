package org.nanotek;

import java.util.Date;
import java.util.concurrent.FutureTask;

import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.CsvBaseProcessor;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.opencsv.task.CsvProcessorCallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
//import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.integration.annotation.IntegrationComponentScan;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.config.EnableIntegrationManagement;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

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
R extends CsvResult<?,?>,
K extends BaseBean<K,ID>,
ID extends BaseEntity<?,?>>  extends 
SpringApplication 
implements 
SpringApplicationRunListener , 
ApplicationRunner{

	
	@Autowired
	@Qualifier(value = "serviceTaskExecutor")
    private ThreadPoolTaskExecutor serviceTaskExecutor;

	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CsvBaseProcessor <T,S,P,M,R> csvBaseProcessor;
	
	@Autowired
	CsvProcessorCallBack<?,?> processor;
	
	
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

		new Thread() {
			@Override
			public void run() {
				CsvResult<?,?> result = null ; 
				FutureTask <R>r;
				log.debug("start time " + new Date());
				do {
					try {
						   csvBaseProcessor.getNext();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}while(processor.isActive());
			}
		}.start();
		log.debug("end time " + new Date());
	}

	
}
