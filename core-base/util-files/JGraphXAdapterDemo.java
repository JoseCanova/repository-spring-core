package org.nanotek.swing;

import com.mxgraph.layout.*;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.orthogonal.mxOrthogonalLayout;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
import org.nanotek.AnyBase;
import org.nanotek.App;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs. Applet based on
 * JGraphAdapterDemo.
 *
 */
//@SpringBootApplication
//@EnableAutoConfiguration(exclude={RabbitAutoConfiguration.class})
//@ComponentScan("org.nanotek")
//@SuppressWarnings("deprecation")
public class JGraphXAdapterDemo
<T extends BaseMap<S,P,M> , 
S  extends AnyBase<S,String> , 
P   extends AnyBase<P,Integer> , 
M extends BaseBean<?,?>, 
R extends CsvResult<?,?>,
K extends BaseBean<K,ID>,
ID extends BaseEntity<?,?>>
    implements 
    SpringApplicationRunListener , 
    ApplicationRunner    
{
    private static final long serialVersionUID = 2202072534703043194L;

    private static final Dimension DEFAULT_SIZE = new Dimension(1024, 800);

    private JGraphXAdapter<Class<? extends BaseEntity>, ?> jgxAdapter;

    
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
	
	
	public JGraphXAdapterDemo() {
		postContruct();
	}
	
	
    private void postContruct() {
		// TODO Auto-generated method s        
        
	}


	/**
     * An alternative starting point for this demo, to also allow running this applet as an
     * application.
     *
     * @param args command line arguments
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {
    	SpringApplication.run(JGraphXAdapterDemo.class, args);
    	 
    }



	@Override
	public void run(ApplicationArguments args) throws Exception {
		 
		 	JFrame frame = new JFrame();
	        frame.setTitle("JGraphT Adapter to JGraphX Demo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
		 	jgxAdapter = new JGraphXAdapter<>(graphModel.getEntityDirectedGraph());

		 	frame.setPreferredSize(DEFAULT_SIZE);
	        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
	        component.setConnectable(false);
	        component.getGraph().setAllowDanglingEdges(false);
	        frame.getContentPane().add(component);
	        frame.resize(DEFAULT_SIZE);

	     
	        mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);
	        layout.setIntraCellSpacing(30);
	        layout.setFineTuning(true);
	        
	        // positioning via jgraphx layouts
//	        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

	        // center the circle
	        int radius = 10;
//	        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
//	        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
//	        layout.setRadius(radius);
//	        layout.setMoveCircle(true);

	        layout.execute(jgxAdapter.getDefaultParent());
	        // that's all there is to it!...	
	}
}
