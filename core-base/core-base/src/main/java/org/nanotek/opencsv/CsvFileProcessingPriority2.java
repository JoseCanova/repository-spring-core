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
import org.nanotek.entities.metamodel.BrainzGraphModel;  
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;  
import org.nanotek.entities.metamodel.MetaModelEdge;  
import org.nanotek.opencsv.metrics.VertexDistance;  
import org.nanotek.opencsv.metrics.VertexPair;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  

@Service  
@SuppressWarnings({"rawtypes","unchecked"})  
public class CsvFileProcessingPriority2<K extends BaseEntity<?,?>>   
implements Priority<K,Long> { // Changed Integer to Long to match movement weights [cite: 1]

    @Autowired   
    BrainzMetaModelUtil brainzMetaModelUtil;  

    @Autowired  
    BrainzGraphModel brainzGraphModel;  

    @Autowired // Inject the new GraphAnalysisService
    private GraphAnalysisService graphAnalysisService;

    Map<Class<?>, Integer> visitFrequency = new HashMap<>();  

    Map<Class<? extends BaseEntity>,Integer> distances = new HashMap<>();  

    public Map<Class<?>, Integer> getVisitFrequency() {  
        return visitFrequency;  
    }  

    public CsvFileProcessingPriority2() {  
    }  

    /**
     * Generates priorities for elements based on the directed graph,
     * using movement weights from GraphAnalysisService as initial priority.
     * @return List of priorities.
     */
    public List<Priority<?,Long>> generatePriorities(){  

        Map<Class<?>,Priority<?,Long>> priorityMap = new HashMap<>();  
        brainzGraphModel.  
        getDirectedGraph()  
            .vertexSet()
            .stream()
            .map(v -> generatePriorityForElement(v)) // Use the updated method
            .forEach(p->priorityMap.put((Class<?>)p.getElement(),p));  

//        processGraphByBreadthFirst(priorityMap);  

//        printVisitFrequency();  

        return priorityMap.values().stream().collect(Collectors.toList());  
    }  

    /**
     * Generates priorities for elements based on the undirected graph,
     * using movement weights from GraphAnalysisService as initial priority.
     * @return List of priorities.
     */
    public List<Priority<?,Long>> generatePrioritiesUndirected(){  

        Map<Class<?>,Priority<?,Long>> priorityMap = new HashMap<>();  
        brainzGraphModel.  
            getEntityGraph()  
            .vertexSet().stream().map( v -> generatePriorityForElementUndirected(v)) // Use the updated method
            .forEach(p->priorityMap.put((Class<?>)p.getElement(),p));  

        processGraphByBreadthFirstUndirected(priorityMap);  

        return priorityMap.values().stream().collect(Collectors.toList());  
    }  

    private void printVisitFrequency() {  
        visitFrequency.entrySet().stream()  
            .sorted((e1,e2)->e2.getValue().compareTo(e1.getValue()))  
            .forEach(e->{  
                System.err.println(e.getKey().getSimpleName() + " : " + e.getValue()); // Added .getSimpleName() for cleaner output
            });  

        System.err.println("Total number of visited elements: " + visitFrequency.size());  
        System.err.println("Total number of elements in graph: " + brainzGraphModel.getEntityDirectedGraph().vertexSet().size());  
        System.err.println("Total number of edges in graph: " + brainzGraphModel.getEntityDirectedGraph().edgeSet().size());   
    }  

    private Class<K> castV(Class<? extends BaseEntity> v) {  
        return (Class<K>) v;  
    }  

    public void processGraphByBreadthFirst(Map<Class<?>,Priority<?,Long>> priorityMap){  

        Graph<Class<? extends BaseEntity> , PriorityEdge> graphDomainModel = brainzGraphModel.getDirectedGraph();  

        DijkstraShortestPath<Class<? extends BaseEntity>,PriorityEdge> dijkstraShortestPath =   
            new DijkstraShortestPath<>(graphDomainModel);  

        HashSet<VertexDistance<?,?>> vertexDistances = new HashSet<>();  

        graphDomainModel.vertexSet().forEach(  
            v->{  
                Set <Object> visitedEdges = new HashSet<>(); // Renamed 'visited' to 'visitedEdges' for clarity [cite: 1]

                BreadthFirstIterator<Class<? extends BaseEntity>,PriorityEdge>  
                    iterator = brainzGraphModel.getBreadthFirstIterator((Class<? extends BaseEntity>)v);  
                while (iterator.hasNext()) {   

                    Class<? extends BaseEntity> next = iterator.next();  
                    Class<? extends BaseEntity> parent = iterator.getParent(next);  
                    if(parent != null) {  
                        if(graphDomainModel.containsEdge(parent , next)) {  

                            if(visitedEdges.contains(graphDomainModel.getEdge(parent, next)))  
                                continue;  
                            else {  
                                visitedEdges.add(graphDomainModel.getEdge(parent, next));  
                                visitFrequency.put(next, visitFrequency.getOrDefault(next, 0) + 1);  
                                double distanceNextFromRoot = dijkstraShortestPath.getPathWeight(v, next);  
                                VertexPair<?,?> rootNextVertexPair = new VertexPair<>(v, next);  
                                VertexDistance<?,?> vertexDistance = new VertexDistance<>(distanceNextFromRoot,rootNextVertexPair);  
                                if(vertexDistances.add(vertexDistance))  
                                {  
                                    printVertexDistance(vertexDistance);  
                                }   
                            }  

                            Priority<?,Long> pnext=priorityMap.get(next);   
                            Priority<?,Long> pparent=priorityMap.get(parent);  
                            
                            // Ensure priorities are not null before operations
                            if (pnext == null || pparent == null || pnext.getPriority() == null || pparent.getPriority() == null) {
                                System.err.println("Warning: Priority not found or null for " + next.getSimpleName() + " or " + parent.getSimpleName() + " during BFS traversal.");
                                continue;
                            }

                            BrainzEntityMetaModel<? extends BaseEntity, Object> parentMetaModel = brainzMetaModelUtil.getMetaModel(parent);  
                            BrainzEntityMetaModel<? extends BaseEntity, Object> nextMetaModel = brainzMetaModelUtil.getMetaModel(next);  
                            MetaModelEdge me = parentMetaModel.getModelGraph().getEdge(parentMetaModel, nextMetaModel);  
                            Double weight = parentMetaModel.getModelGraph().getEdgeWeight(me);  
                            
                            if (weight == 2.0d || weight == 1.0d ) { // This condition remains as per original logic [cite: 1]
                                System.err.println("Parent " + parent.getSimpleName());  // Added .getSimpleName()
                                // Update priorities based on traversal and existing priority values
                                Priority<?,Long> pnextP = Priority.createPriorityElement(next, pparent.getPriority() + pnext.getPriority() + 1L);  
                                Priority<?,Long> pparentP = Priority.createPriorityElement(parent, pnext.getPriority() + 1L);  
                                priorityMap.put(parent, pparentP);  
                                priorityMap.put(next, pnextP);  
                            }  
                        }  
                    }  
                }});  

        applyInverseFrequencyScalling(priorityMap,visitFrequency);  
    }  

    //TODO: use this method as a use case to understand \"missing relations\" check the report at https://github.com/JoseCanova/brainz/wiki/Recommendations-for-Scoring-Adjustments  
    private void applyInverseFrequencyScalling(Map<Class<?>, Priority<?, Long>> priorityMap, Map<Class<?>, Integer> visitFrequency2) {  
        priorityMap  
            .keySet()  
            .forEach(k -> {  
                Integer frequency= visitFrequency.get(k);   
                if (frequency !=null) {  
                    Priority<?, Long> priority = priorityMap.get(k);  
                    Optional  
                        .ofNullable(priority)  
                        .filter(p -> p.getPriority()!=null)  
                        .map(p -> p.getPriority())  
                        .ifPresent(prior->{  
                            // Ensure frequency is not zero to avoid division by zero or log(0) issues
                            double newPriority = prior / Math.log(frequency + Math.E); 
                            System.err.println(k.getSimpleName() + " " + newPriority);  // Added .getSimpleName()
                            // Update the priority in the map with the scaled value
                            priorityMap.put(k, Priority.createPriorityElement(k, (long) newPriority));
                        });  
                }  
            });  
    }  

    private void printVertexDistance(VertexDistance<?, ?> vertexDistance) {  
        System.err.println("Vertex Distance: " + vertexDistance.getDistance() +   
            " between " + Class.class.cast (vertexDistance.getVertexPair().getSource()).getSimpleName() +  
            " and " + Class.class.cast (vertexDistance.getVertexPair().getTarget()).getSimpleName());  
    }  

    public void processGraphByBreadthFirstUndirected(Map<Class<?>,Priority<?,Long>> priorityMap){  

        brainzGraphModel.getEntityGraph().vertexSet().forEach(v->{  

            BreadthFirstIterator<Class<? extends BaseEntity>,PriorityEdge>  
                iterator = brainzGraphModel.getBreadthFirstIteratorUndirected((Class<? extends BaseEntity>)v);  
            while (iterator.hasNext()) {   
                Class<? extends BaseEntity> next = iterator.next();  
                Class<? extends BaseEntity> parent = iterator.getParent(next);  
                if(brainzGraphModel.getEntityGraph().containsEdge(parent , next)) {  
                    Priority<?,Long> pnext=priorityMap.get(next);   
                    Priority<?,Long> pparent=priorityMap.get(parent);  
                    
                    // Ensure priorities are not null before operations
                    if (pnext == null || pparent == null || pnext.getPriority() == null || pparent.getPriority() == null) {
                        System.err.println("Warning: Priority not found or null for " + next.getSimpleName() + " or " + parent.getSimpleName() + " during undirected BFS traversal.");
                        continue;
                    }

                    if(pparent.getPriority() >= pnext.getPriority()) {   
                        Priority<?,Long> pnextP = Priority.createPriorityElement(next, pparent.getPriority()+pnext.getPriority() +1L);  
                        Priority<?,Long> pparentP = Priority.createPriorityElement(parent, pnext.getPriority() + 1L);  
                        priorityMap.put(parent, pparentP);  
                        priorityMap.put(next, pnextP);  
                    }  
                }  
            }  
        });  
    }  

    /**
     * Generates a priority for an element by looking up its movement weight.
     * @param element The class for which to generate priority.
     * @return A Priority object with the movement weight.
     */
    public Priority<Class<K>, Long> generatePriorityForElement(Class<? extends BaseEntity> element) {  
        Long priority = graphAnalysisService.getMovementWeights().get(element);
        // If for some reason the element is not in movement weights, default to 0
        return (Priority<Class<K>, Long>) generatePriorityForElement(element, priority != null ? priority : 0L);  
    }  

    /**
     * Generates a priority for an element by looking up its movement weight (for undirected context).
     * Currently reuses the same movement weights as directed.
     * @param element The class for which to generate priority.
     * @return A Priority object with the movement weight.
     */
    public Priority<?, Long> generatePriorityForElementUndirected(Class<?> element) {  
        Long priority = graphAnalysisService.getMovementWeights().get(element);
        return generatePriorityForElement(element, priority != null ? priority : 0L);  
    }  

    private <V extends Priority<E,P>,E,P extends Comparable<P>>   
    Priority<?,P> generatePriorityForElement(E element,P priority) {  
        return new Priority<E,P>() {  

            private P priorityValue = priority;  

            private E elementValue = element;  

            public P getPriority() {  
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