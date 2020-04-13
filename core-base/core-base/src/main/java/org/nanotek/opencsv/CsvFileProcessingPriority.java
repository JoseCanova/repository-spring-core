package org.nanotek.opencsv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jgrapht.traverse.BreadthFirstIterator;
import org.nanotek.BaseEntity;
import org.nanotek.Priority;
import org.nanotek.entities.metamodel.BrainzGraphModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class CsvFileProcessingPriority<K extends BaseEntity<?,?>> 
implements Priority<K,Integer>{

	@Autowired
	BrainzGraphModel brainzGraphModel;

	public CsvFileProcessingPriority() {
	}

	public List<Priority<?,Integer>> generatePriorities(){

		Map<Class<?>,Priority<?,Integer>> priorityMap = new HashMap<Class<?>,Priority<?,Integer>>();
		brainzGraphModel.
		getEntityDirectedGraph()
		.vertexSet().stream().map(v->{
			Priority<?,Integer> p = generatePriorityForElement(castV(v));
			return p;
		})
		.forEach(p->priorityMap.put((Class<?>)p.getElement(),p));
		//		

		processGraphByBreadthFirst(priorityMap);

		return priorityMap.values().stream().collect(Collectors.toList());
	}

	public List<Priority<?,Integer>> generatePrioritiesUndirected(){

		Map<Class<?>,Priority<?,Integer>> priorityMap = new HashMap<Class<?>,Priority<?,Integer>>();
		brainzGraphModel.
		getEntityGraph()
		.vertexSet().stream().map(v->{
			Priority<?,Integer> p = generatePriorityForElementUndirected(castV(v));
			return p;
		})
		.forEach(p->priorityMap.put((Class<?>)p.getElement(),p));
		//		

		processGraphByBreadthFirstUndirected(priorityMap);

		return priorityMap.values().stream().collect(Collectors.toList());
	}
	
	
	private Class<K> castV(Class<? extends BaseEntity> v) {
		return (Class<K>) v;
	}

	public void processGraphByBreadthFirst(Map<Class<?>,Priority<?,Integer>> priorityMap){
		
		brainzGraphModel.getEntityDirectedGraph().vertexSet().forEach(v->{
		
		BreadthFirstIterator<Class<? extends BaseEntity>,PriorityEdge>
		iterator = brainzGraphModel.getBreadthFirstIterator((Class<? extends BaseEntity>)v);
		while (iterator.hasNext()) { 
			Class<? extends BaseEntity> next = iterator.next();
			Class<? extends BaseEntity> parent = iterator.getParent(next);
			if(brainzGraphModel.getEntityDirectedGraph().containsEdge(parent , next)) {
				Priority<?,Integer> pnext=priorityMap.get(next); 
				Priority<?,Integer> pparent=priorityMap.get(parent);
				if(pparent.getPriority()>=pnext.getPriority()) { 
					Priority<?,Integer> pnextP =  Priority.createPriorityElement(next, pparent.getPriority()+pnext.getPriority() +1);
					Priority<?,Integer> pparentP =  Priority.createPriorityElement(parent, pnext.getPriority() + 1);
					priorityMap.put(parent, pparentP);
					priorityMap.put(next, pnextP);
				}
			}

		}});
	}
	
public void processGraphByBreadthFirstUndirected(Map<Class<?>,Priority<?,Integer>> priorityMap){
		
		brainzGraphModel.getEntityGraph().vertexSet().forEach(v->{
		
		BreadthFirstIterator<Class<? extends BaseEntity>,PriorityEdge>
		iterator = brainzGraphModel.getBreadthFirstIteratorUndirected((Class<? extends BaseEntity>)v);
		while (iterator.hasNext()) { 
			Class<? extends BaseEntity> next = iterator.next();
			Class<? extends BaseEntity> parent = iterator.getParent(next);
			if(brainzGraphModel.getEntityGraph().containsEdge(parent , next)) {
				Priority<?,Integer> pnext=priorityMap.get(next); 
				Priority<?,Integer> pparent=priorityMap.get(parent);
				if(pparent.getPriority()>=pnext.getPriority()) { 
					Priority<?,Integer> pnextP =  Priority.createPriorityElement(next, pparent.getPriority()+pnext.getPriority() +1);
					Priority<?,Integer> pparentP =  Priority.createPriorityElement(parent, pnext.getPriority() + 1);
					priorityMap.put(parent, pparentP);
					priorityMap.put(next, pnextP);
				}
			}

		}});
	}


	public Priority<?, Integer> generatePriorityForElement(Class<K> element) {

		Integer priority = brainzGraphModel
				.getEntityDirectedGraph()
				.edgeSet()
				.stream()
				.map(ep -> {
					PriorityEdge pe= PriorityEdge.class.cast(ep);
					int res=0;
					if(pe.getSource().equals(element))
						res = 1;
					return res;})
				.reduce(0, Integer::sum);
		return generatePriorityForElement(element,priority );
	}

	
	public Priority<?, Integer> generatePriorityForElementUndirected(Class<K> element) {

		Integer priority = brainzGraphModel
				.getEntityGraph()
				.edgeSet()
				.stream()
				.map(ep -> {
					PriorityEdge pe= PriorityEdge.class.cast(ep);
					int res=0;
					if(pe.getSource().equals(element))
						res = 1;
					return res;})
				.reduce(0, Integer::sum);
		return generatePriorityForElement(element,priority );
	}

	private <V extends Priority<E,P>,E,P extends Comparable<P>> 
	Priority<?,P> generatePriorityForElement(E element,P priority) {
		return  new Priority<E,P>() {

			private P priorityValue = priority;

			private E elementValue = element;


			public P  getPriority() {
				return priorityValue;
			}

			public E getElement() {
				return elementValue;
			}
			@Override
			public int compare(P o1, P o2) {
				return o1.compareTo(o2);
			}

		};
	}

}
