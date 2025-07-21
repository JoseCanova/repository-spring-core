package org.nanotek.opencsv;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jgrapht.Graph;
import org.jgrapht.alg.planar.BoyerMyrvoldPlanarityInspector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.BaseEntity;
import org.nanotek.graph.brainz.MusicBrainzKnowledgeGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestIsBrainzGraphModelIsPlanar {

	@Autowired
	MusicBrainzKnowledgeGraph brainzGraphModel;
	
	@Test
	void testIsBrainzGraphModelIsPlanar() {
		assertNotNull(brainzGraphModel);
		Graph<Class<? extends BaseEntity>, ?> theGraph = brainzGraphModel.getEntityDirectedGraph();
        BoyerMyrvoldPlanarityInspector<Class<? extends BaseEntity>, ?> planarityTest = 
        					new BoyerMyrvoldPlanarityInspector<>(theGraph);

        assertTrue(planarityTest.isPlanar());
	
	
	}	
	
}
