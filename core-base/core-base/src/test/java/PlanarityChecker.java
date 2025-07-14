/**
 * 
Explanation:
import Statements: We import Graph (the interface for graphs), BoyerMyrvoldPlanarityTest (the algorithm), and SimpleGraph and DefaultEdge (for creating simple example graphs).

isGraphPlanar Method:

It's a generic method (<V, E>) that can work with any vertex (V) and edge (E) type used in your JGraphT graph.

It instantiates BoyerMyrvoldPlanarityTest by passing the graph to its constructor.

It then calls the isPlanar() method on the test object, which performs the actual algorithm and returns true or false.

Important Note: The Boyer-Myrvold algorithm, and the definition of planarity itself, primarily applies to undirected graphs. If you pass a DefaultDirectedGraph to this method, it might throw an IllegalArgumentException or produce unexpected results, as JGraphT's implementation is specific to undirected graphs for planarity. SimpleGraph is inherently undirected, so it works perfectly here.

main Method (Examples):

C4 (Square): A very simple planar graph. The output will be true.

K5 (Complete Graph on 5 Vertices): This is one of the two fundamental non-planar graphs, according to Kuratowski's Theorem. The output will be false.

K3,3 (Complete Bipartite Graph): This is the other fundamental non-planar graph. The output will be false.

This algorithm provides a robust and efficient way to determine graph planarity using the JGraphT library.

 * PlanarityChecker.java
 * 
 */

import org.jgrapht.Graph;
import org.jgrapht.alg.planar.BoyerMyrvoldPlanarityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * A utility class to check the planarity of graphs using JGraphT.
 */
public class PlanarityChecker {

    /**
     * Determines if a given graph is planar using the Boyer-Myrvold algorithm.
     *
     * @param graph The JGraphT Graph instance to test.
     * @param <V> The vertex type of the graph.
     * @param <E> The edge type of the graph.
     * @return true if the graph is planar, false otherwise.
     * @throws IllegalArgumentException if the provided graph is directed,
     * as planarity is typically defined for undirected graphs.
     * BoyerMyrvoldPlanarityTest only works for undirected graphs.
     */
    public static <V, E> boolean isGraphPlanar(Graph<V, E> graph) {
        // JGraphT's BoyerMyrvoldPlanarityTest requires an undirected graph.
        // SimpleGraph is inherently undirected. If you were using DefaultDirectedGraph
        // you would first need to convert it to an undirected representation or handle the error.
        
        // Instantiate the Boyer-Myrvold planarity test algorithm.
        BoyerMyrvoldPlanarityInspector<V, E> planarityTest = new BoyerMyrvoldPlanarityInspector<>(graph);

        // Run the planarity test and return the result.
        return planarityTest.isPlanar();
    }

    public static void main(String[] args) {
        // --- Example 1: A Planar Graph (a simple square/cycle graph C4) ---
        Graph<String, DefaultEdge> c4Graph = new SimpleGraph<>(DefaultEdge.class);
        c4Graph.addVertex("A");
        c4Graph.addVertex("B");
        c4Graph.addVertex("C");
        c4Graph.addVertex("D");
        c4Graph.addEdge("A", "B");
        c4Graph.addEdge("B", "C");
        c4Graph.addEdge("C", "D");
        c4Graph.addEdge("D", "A");
        System.out.println("Is C4 (square) planar? " + isGraphPlanar(c4Graph)); // Expected: true

        // --- Example 2: A Non-Planar Graph (K5 - Complete Graph on 5 vertices) ---
        // K5 is one of the two minimal non-planar graphs (Kuratowski's Theorem)
        Graph<String, DefaultEdge> k5Graph = new SimpleGraph<>(DefaultEdge.class);
        k5Graph.addVertex("V1");
        k5Graph.addVertex("V2");
        k5Graph.addVertex("V3");
        k5Graph.addVertex("V4");
        k5Graph.addVertex("V5");
        // Add all possible edges
        k5Graph.addEdge("V1", "V2"); k5Graph.addEdge("V1", "V3"); k5Graph.addEdge("V1", "V4"); k5Graph.addEdge("V1", "V5");
        k5Graph.addEdge("V2", "V3"); k5Graph.addEdge("V2", "V4"); k5Graph.addEdge("V2", "V5");
        k5Graph.addEdge("V3", "V4"); k5Graph.addEdge("V3", "V5");
        k5Graph.addEdge("V4", "V5");
        System.out.println("Is K5 (complete graph on 5 vertices) planar? " + isGraphPlanar(k5Graph)); // Expected: false

        // --- Example 3: Another Non-Planar Graph (K3,3 - Complete Bipartite Graph) ---
        // K3,3 is the other minimal non-planar graph (Kuratowski's Theorem)
        Graph<String, DefaultEdge> k3_3Graph = new SimpleGraph<>(DefaultEdge.class);
        k3_3Graph.addVertex("U1"); k3_3Graph.addVertex("U2"); k3_3Graph.addVertex("U3");
        k3_3Graph.addVertex("V1"); k3_3Graph.addVertex("V2"); k3_3Graph.addVertex("V3");
        // Edges connect U-set to V-set
        k3_3Graph.addEdge("U1", "V1"); k3_3Graph.addEdge("U1", "V2"); k3_3Graph.addEdge("U1", "V3");
        k3_3Graph.addEdge("U2", "V1"); k3_3Graph.addEdge("U2", "V2"); k3_3Graph.addEdge("U2", "V3");
        k3_3Graph.addEdge("U3", "V1"); k3_3Graph.addEdge("U3", "V2"); k3_3Graph.addEdge("U3", "V3");
        System.out.println("Is K3,3 (complete bipartite graph) planar? " + isGraphPlanar(k3_3Graph)); // Expected: false
    }
}