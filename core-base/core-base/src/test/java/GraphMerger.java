import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph; 
// Or whatever graph type your mainGraph is
// ... other necessary imports for your vertex and edge types
import org.nanotek.BaseEntity;
import org.nanotek.opencsv.PriorityEdge;

public class GraphMerger {
//
//    // Assuming mainGraph is already initialized, e.g., your entityDirectedGraph
//    private Graph<Class<? extends BaseEntity>, PriorityEdge> mainGraph; 
//
//    public GraphMerger(Graph<Class<? extends BaseEntity>, PriorityEdge> mainGraph) {
//        this.mainGraph = mainGraph;
//    }
//
//    /**
//     * Merges the contents (vertices and edges) of a source graph into the main graph.
//     * This method can be called sequentially for multiple source graphs.
//     *
//     * @param sourceGraph The graph whose vertices and edges will be added to the mainGraph.
//     */
//    public void mergeGraph(Graph<Class<? extends BaseEntity>, PriorityEdge> sourceGraph) {
//        // 1. Add all vertices from the sourceGraph to the mainGraph
//        // It's safe to add existing vertices; JGraphT will simply not add duplicates.
//        for (Class<? extends BaseEntity> vertex : sourceGraph.vertexSet()) {
//            mainGraph.addVertex(vertex);
//        }
//
//        // 2. Add all edges from the sourceGraph to the mainGraph
//        // When adding edges, JGraphT requires both source and target vertices to exist
//        // in the graph. Step 1 ensures this for all vertices from sourceGraph.
//        for (PriorityEdge edge : sourceGraph.edgeSet()) {
//            Class<? extends BaseEntity> sourceVertex = sourceGraph.getEdgeSource(edge);
//            Class<? extends BaseEntity> targetVertex = sourceGraph.getEdgeTarget(edge);
//
//            // JGraphT's addEdge method will return null if the edge already exists,
//            // or if a problem occurs (e.g., vertex not found, though we've added them).
//            // It's robust to adding existing edges (it won't create duplicates based on equality).
//            mainGraph.addEdge(sourceVertex, targetVertex, edge);
//        }
//
//        System.out.println("Merged a graph. Current mainGraph size: " 
//                            + mainGraph.vertexSet().size() + " vertices, " 
//                            + mainGraph.edgeSet().size() + " edges.");
//    }
//
//    // You can also create a static utility method if you prefer
//    public static <V, E> Graph<V, E> createMergedGraph(
//        Graph<V, E> initialGraph, 
//        List<Graph<V, E>> graphsToMerge) {
//
////        Graph<V, E> mergedGraph = new DefaultDirectedGraph<>(initialGraph.getEdgeSupplier()); // Or copy initialGraph
//        // Copy initial graph's content
//        for (V vertex : initialGraph.vertexSet()) {
//            mergedGraph.addVertex(vertex);
//        }
//        for (E edge : initialGraph.edgeSet()) {
//            mergedGraph.addEdge(initialGraph.getEdgeSource(edge), initialGraph.getEdgeTarget(edge), edge);
//        }
//
//        for (Graph<V, E> sourceGraph : graphsToMerge) {
//            for (V vertex : sourceGraph.vertexSet()) {
//                mergedGraph.addVertex(vertex);
//            }
//            for (E edge : sourceGraph.edgeSet()) {
//                V sourceVertex = sourceGraph.getEdgeSource(edge);
//                V targetVertex = sourceGraph.getEdgeTarget(edge);
//                mergedGraph.addEdge(sourceVertex, targetVertex, edge);
//            }
//        }
//        return mergedGraph;
//    }
}