package org.nanotek.opencsv;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.nanotek.BaseEntity;
import org.nanotek.Priority;
import org.nanotek.entities.metamodel.BrainzEntityMetaModel;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;
import org.nanotek.entities.metamodel.MetaModelEdge;
import org.nanotek.graph.brainz.MusicBrainzKnowledgeGraph;
import org.nanotek.opencsv.metrics.VertexDistance;
import org.nanotek.opencsv.metrics.VertexPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class CsvFileProcessingPriority<K extends BaseEntity<?,?>> 
implements Priority<K,Integer> {

	@Autowired 
	BrainzMetaModelUtil brainzMetaModelUtil;
	
	@Autowired
	MusicBrainzKnowledgeGraph brainzGraphModel;

	Map<Class<?>, Integer> visitFrequency = new HashMap<>();
	
	Map<Class<? extends BaseEntity>,Integer> distances = new HashMap<>();
	
	public Map<Class<?>, Integer> getVisitFrequency() {
		return visitFrequency;
	}
	
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
		
		printVisitFrequency();

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


	private void printVisitFrequency() {
		visitFrequency.entrySet().stream()
		.sorted((e1,e2)->e2.getValue().compareTo(e1.getValue()))
		.forEach(e->{
			System.err.println(e.getKey() + " : " + e.getValue());
		});
		
		System.err.println("Total number of visited elements: " + visitFrequency.size());
		System.err.println("Total number of elements in graph: " + brainzGraphModel.getEntityDirectedGraph().vertexSet().size());
		
		System.err.println("Total number of edges in graph: " + brainzGraphModel.getEntityDirectedGraph().edgeSet().size());		
	}

	private Class<K> castV(Class<? extends BaseEntity> v) {
		return (Class<K>) v;
	}
	
	

	public void processGraphByBreadthFirst(Map<Class<?>,Priority<?,Integer>> priorityMap){
		
		
		Graph<Class<? extends BaseEntity> , PriorityEdge>  graphDomainModel = brainzGraphModel.getDirectedGraph();
		
		DijkstraShortestPath<Class<? extends BaseEntity>,PriorityEdge> dijkstraShortestPath = 
				new DijkstraShortestPath<>(graphDomainModel);
		
		HashSet<VertexDistance<?,?>> vertexDistances = new HashSet<>();
		
		graphDomainModel.vertexSet().forEach(
		v->{
			
			Set <Object> visited = new HashSet<>();
			
			BreadthFirstIterator<Class<? extends BaseEntity>,PriorityEdge>
			iterator = brainzGraphModel.getBreadthFirstIterator((Class<? extends BaseEntity>)v);
			while (iterator.hasNext()) { 
				
				Class<? extends BaseEntity> next = iterator.next();
				Class<? extends BaseEntity> parent = iterator.getParent(next);
				if(parent != null) {
					if(graphDomainModel.containsEdge(parent , next)) {
						
						if(visited.contains(graphDomainModel.getEdge(parent, next)))
							continue;
						else {
							visited.add(graphDomainModel.getEdge(parent, next));
							visitFrequency.put(next, visitFrequency.getOrDefault(next, 0) + 1);
							double distanceNextFromRoot = dijkstraShortestPath.getPathWeight(v, next);
							VertexPair<?,?> rootNextVertexPair = new VertexPair<>(v, next);
							VertexDistance<?,?> vertexDistance = new VertexDistance<>(distanceNextFromRoot,rootNextVertexPair);
							if(vertexDistances.add(vertexDistance))
							{
								printVertexDistance(vertexDistance);
							}					 
						}
						
						Priority<?,Integer> pnext=priorityMap.get(next); 
						Priority<?,Integer> pparent=priorityMap.get(parent);
						BrainzEntityMetaModel<? extends BaseEntity, Object> parentMetaModel = brainzMetaModelUtil.getMetaModel(parent);
						BrainzEntityMetaModel<? extends BaseEntity, Object> nextMetaModel  = brainzMetaModelUtil.getMetaModel(next);
						MetaModelEdge me  = parentMetaModel.getModelGraph().getEdge(parentMetaModel, nextMetaModel);
						Double weight = parentMetaModel.getModelGraph().getEdgeWeight(me);
	//					if(pparent.getPriority()>=pnext.getPriority()) { 
						if (weight == 2.0d || weight == 1.0d ) {
								System.err.println("Parent " + parent);
								Priority<?,Integer> pnextP =  Priority.createPriorityElement(next, pparent.getPriority()+pnext.getPriority() +1);
								Priority<?,Integer> pparentP =  Priority.createPriorityElement(parent, pnext.getPriority() + 1);
								priorityMap.put(parent, pparentP);
								priorityMap.put(next, pnextP);
						}
					   }
					}
			}});
		
		applyInverseFrequencyScalling(priorityMap,visitFrequency);
	}

	//TODO: use this method as a use case to understand "missing relations" check the report at https://github.com/JoseCanova/brainz/wiki/Recommendations-for-Scoring-Adjustments
	private void applyInverseFrequencyScalling(Map<Class<?>, Priority<?, Integer>> priorityMap, Map<Class<?>, Integer> visitFrequency2) {
		// TODO Auto-generated method stub
		
		priorityMap
		.keySet()
		.forEach(k -> {
			Integer frequency= visitFrequency.get(k);	
					if (frequency !=null) {
					Priority<?, Integer> priority = priorityMap.get(k);
					Optional
					.ofNullable(priority)
					.filter(p -> p.getPriority()!=null)
					.map(p -> p.getPriority())
					.ifPresent(prior->{
						double newPriority = prior / Math.log(frequency + Math.E);
						System.err.println(k.getSimpleName() + " " + newPriority);
					});
					}
		});
		
	}

	private void printVertexDistance(VertexDistance<?, ?> vertexDistance) {
		System.err.println("Vertex Distance: " + vertexDistance.getDistance() + 
				" between " + Class.class.cast (vertexDistance.getVertexPair().getSource()).getSimpleName() +
				" and " + Class.class.cast (vertexDistance.getVertexPair().getTarget()).getSimpleName());
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
