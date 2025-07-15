package org.nanotek.opencsv;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.BaseEntity;
import org.nanotek.Priority;
import org.nanotek.Priority.PriorityComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApplicationStartTest2 {

	
	@Autowired 
	ApplicationContext context;
	
	@Autowired
	GraphAnalysisService graphAnalysisService;
	
	@Autowired 
	CsvFileProcessingPriority2<?> processingPriority;
	
	@Test
	void testApplicationContextInjection() {
		assertNotNull(context);
		assertNotNull(graphAnalysisService);
		assertNotNull(processingPriority);
		
		PriorityQueue<Priority<?, Long>> pq = new PriorityQueue<Priority<?,Long>>(new PriorityComparator<Long>());

		List<Priority<?,Long>>  pList = processingPriority.generatePriorities();

		pList.forEach(p->{
			pq.add(p);
		});
		
		Priority<?,Long> prior = null;
		do {
			prior = pq.poll();
			if (prior !=null)
				System.out.println("new priotity" + prior.getElement() + "  " + prior.getPriority());
		}while(prior !=null);
	}
}
