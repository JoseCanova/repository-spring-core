package org.nanotek.entities.metamodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.nio.graphml.GraphMLExporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BrainzMetaModelUtilTest {

	@Autowired
	BrainzMetaModelUtil brainzMetaModelUtil;
	DOTExporter<BrainzEntityMetaModel<?,?>, MetaModelEdge> dotExporter;
	
	GraphMLExporter<BrainzEntityMetaModel<?,?>, MetaModelEdge> graphMLExporter;
	
	String targetDirectory ;
	@BeforeEach
	void setUp() {
		assertNotNull(brainzMetaModelUtil);
		 targetDirectory = "/home/jose/testresults/graph/";
        Path directoryPath = Paths.get(targetDirectory);
        try {
			Files.createDirectories(directoryPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Create directory if it doesn't exist

	}
	
	@Test
	public void testAfterPropertiesSet() throws Exception {
		brainzMetaModelUtil.afterPropertiesSet();
		assertNotNull(brainzMetaModelUtil.getMetaModelMap());
		assertNotNull(brainzMetaModelUtil.getMetaModel());
		Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge> simpleGraph = buildDirectedSimpleGraph();
		
		Map<Class<?> , BrainzEntityMetaModel<?,?>> metaModelMap = brainzMetaModelUtil.getMetaModelMap();

	   DOTExporter<BrainzEntityMetaModel<?,?>, MetaModelEdge> exporter = new DOTExporter<>(vertex -> vertex.getEntityClassName()); // Simple vertex ID provider
		graphMLExporter = new GraphMLExporter<BrainzEntityMetaModel<?,?>, MetaModelEdge>(vertex -> vertex.getEntityClassName()); 
		graphMLExporter.setExportEdgeWeights(true);
	   
	   System.err.println(metaModelMap);
		metaModelMap
		.values()
		.stream()
		.forEach(m -> {
			 Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge> theGraph = m.getModelGraph();
		     Graphs.addGraph(simpleGraph,theGraph);
		        String filePath = targetDirectory + m.getEntityClassName();
		        try (FileWriter fileWriter = new FileWriter(filePath)) {
		            exporter.exportGraph(theGraph, fileWriter);
		            System.out.println("Graph exported successfully to " + filePath);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		});
	
		String filePath = targetDirectory + "big-graph";
		try (FileWriter fileWriter = new FileWriter(filePath)) {
            exporter.exportGraph(simpleGraph, fileWriter);
            System.out.println("Graph exported successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		String gfilePath = targetDirectory + "big-graph.gml";
		try (FileWriter fileWriter = new FileWriter(gfilePath)) {
			graphMLExporter.exportGraph(simpleGraph, fileWriter);
            System.out.println("Graph exported successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

	
	}
	
	private  Graph<BrainzEntityMetaModel<?,?>, MetaModelEdge> buildDirectedSimpleGraph() {
		return GraphTypeBuilder
				.<BrainzEntityMetaModel<?,?>, MetaModelEdge>directed() .allowingMultipleEdges(true)
				.allowingSelfLoops(true).edgeClass(MetaModelEdge.class).weighted(true).buildGraph();
	}
	
}
