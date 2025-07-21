package org.nanotek.opencsv;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.opencsv.metrics.VertexWeigth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GraphAnalysisServiceTest {

	@Autowired 
	GraphAnalysisService2 graphAnalysisService;
	
	@Test
	void countextLoad() {
		assertNotNull(graphAnalysisService);
	}
	
	@Test
	void printPriorityQueue(){
		assertNotNull(graphAnalysisService);
		PriorityQueue<VertexWeigth> pq = graphAnalysisService.getProcessingPriorityQueue();
		VertexWeigth entityClass = null;
		while ((entityClass = pq.poll()) !=null ) {
			System.err.println("Class in priorityQueue : " + entityClass.get());
		}
	}

}
