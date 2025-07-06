package org.nanotek.entities.metamodel;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jgrapht.Graph;
import org.jgrapht.nio.dot.DOTExporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.BaseEntity;
import org.nanotek.opencsv.PriorityEdge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BrainzGraphModelTest {

	@Autowired
	BrainzGraphModel brainzGraphModel;
	DOTExporter <Class<? extends BaseEntity>, PriorityEdge>dotExporter;
	private String targetDirectory;

	
	@BeforeEach
	void setUp() {
		assert brainzGraphModel != null;
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
		brainzGraphModel.afterPropertiesSet();
		assert brainzGraphModel.getEntityDirectedGraph() != null;
		assert brainzGraphModel.getMetaModel() != null;
		assert brainzGraphModel.getMetamodelEntities() != null;
		assert brainzGraphModel.getMetaModel().getManagedTypes() != null;
		assert brainzGraphModel.getMetaModel().getManagedTypes().size() > 0;
		
		dotExporter = new DOTExporter<>(vertex -> vertex.getSimpleName()); // Simple vertex ID provider
		
		String filePath = targetDirectory + "directbig-graph";

		try (FileWriter fileWriter = new FileWriter(filePath)) {
			dotExporter.exportGraph(brainzGraphModel.getDirectedGraph(), fileWriter);
            System.out.println("Graph exported successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
}
