package org.nanotek;

import java.util.Date;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.JohnsonShortestPaths;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.Gender;
import org.nanotek.beans.entity.Release;
import org.nanotek.beans.entity.ReleaseAlias;
import org.nanotek.collections.BaseMap;
import org.nanotek.entities.metamodel.BrainzGraphModel;
import org.nanotek.opencsv.CsvBaseProcessor;
import org.nanotek.opencsv.CsvFileProcessingPriority;
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
	
	@Autowired
	BrainzGraphModel graphModel;
	
	@Autowired 
	CsvFileProcessingPriority priorityMaker;

	
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

		priorityMaker.generatePriorities();
		
		
//		graphModel.
//				getEntityDirectedGraph()
//				.vertexSet().forEach(v->{
//					Priority<?,Integer> p = 	priorityMaker.generatePriorityForElement(v);
//					System.out.println(v.getName() + " " + p.getPriority());
//				});;
		
		
		
		
//	Priority<?,Integer> p = 	priorityMaker.generatePriorityForElement(Artist.class);
//	Priority<?,Integer> p1 = 	priorityMaker.generatePriorityForElement(Area.class);
//	Priority<?,Integer> p2 = 	priorityMaker.generatePriorityForElement(Release.class);
//	Priority<?,Integer> p3 = 	priorityMaker.generatePriorityForElement(AreaType.class);
//	Priority<?,Integer> p4 = 	priorityMaker.generatePriorityForElement(ReleaseAlias.class);
//	Priority<?,Integer> p5 = 	priorityMaker.generatePriorityForElement(Gender.class);
//		System.out.println(p.getPriority());
//		System.out.println(p1.getPriority());
//		System.out.println(p2.getPriority());
//		System.out.println(p3.getPriority());
//		System.out.println(p4.getPriority());
//		System.out.println(p5.getPriority());
		
		JohnsonShortestPaths jsp = new JohnsonShortestPaths(graphModel.getEntityGraph());
		GraphPath  path=  jsp.getPath(Artist.class,Release.class);
		GraphPath  path1=  jsp.getPath(Release.class,Artist.class);
		System.out.println(path);
		System.out.println(path1);
//		new Thread() {
//			@Override
//			public void run() {
//				CsvResult<?,?> result = null ; 
//				FutureTask <R>r;
//				log.debug("start time " + new Date());
//				do {
//					try {
//						   csvBaseProcessor.getNext();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}while(processor.isActive());
//			}
//		}.start();
		log.debug("end time " + new Date());
	}


	/**
	 * 		System.out.println(entityGraph.containsVertex(Artist.class));
		System.out.println(entityGraph.containsVertex(ArtistCredit.class));
		
		JohnsonShortestPaths jsp = new JohnsonShortestPaths(entityGraph);
						
		GraphPath<Class<? extends BaseEntity>, ?> path=  jsp.getPath(ArtistCredit.class, Area.class);
		
		GraphMeasurer measurer = new GraphMeasurer(entityGraph,jsp);
		System.out.println(measurer.getDiameter());
		System.out.println(measurer.getRadius());
		if (path !=null)
			System.out.println(path.toString());
		path=  jsp.getPath(Artist.class, Recording.class);
		if (path !=null)
			System.out.println(path.toString());
		path=  jsp.getPath(Artist.class, Release.class);
		if (path !=null)
			System.out.println(path.toString());
		System.out.println(entityGraph.toString());
		new org.jgrapht.nio.json.JSONExporter().exportGraph(entityGraph, System.out);
	 */
	
}
