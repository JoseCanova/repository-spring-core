import org.jgrapht.Graph;
//import org.jgrapht.alg.connectivity.LineGraph; // This is the class for line graphs
import org.jgrapht.alg.transform.LineGraphConverter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class LineGraphTransformer {

    /**
     * Transforms a given graph into its Line Graph representation.
     * In a Line Graph, each vertex represents an edge from the original graph,
     * and two vertices are connected if their corresponding edges in the original
     * graph share a common vertex (are incident).
     *
     * @param originalGraph The input graph. It must be undirected for LineGraph to work correctly.
     * @param <V> The vertex type of the original graph.
     * @param <E> The edge type of the original graph.
     * @return The Line Graph of the original graph.
     */
    public static <V, E> Graph<DefaultEdge, DefaultEdge> createLineGraph(Graph<V, DefaultEdge> originalGraph) {
        // The LineGraph class in JGraphT takes the original graph
        // and produces a new graph where vertices are the original edges.
        // DefaultEdge is used for the edges in the line graph.
    	LineGraphConverter<V,DefaultEdge,DefaultEdge> converter = new LineGraphConverter<>(originalGraph);
    	Graph<DefaultEdge, DefaultEdge> lineGraph = new SimpleGraph<>(DefaultEdge::new , DefaultEdge::new , false);

        // Get the actual line graph representation
        converter.convertToLineGraph(lineGraph);
        return lineGraph;
    }

    public static void main(String[] args) {
        // --- Example 1: A Simple Path Graph (P3: A-B-C) ---
        Graph<String, DefaultEdge> pathGraph = new SimpleGraph<>(DefaultEdge.class);

        pathGraph.addVertex("A");
        pathGraph.addVertex("B");
        pathGraph.addVertex("C");
        DefaultEdge ab = pathGraph.addEdge("A", "B");
        DefaultEdge bc = pathGraph.addEdge("B", "C");

        System.out.println("--- Original Path Graph (P3) ---");
        System.out.println("Vertices: " + pathGraph.vertexSet());
        System.out.println("Edges: " + pathGraph.edgeSet()); // Edges: [(A,B), (B,C)]
        System.out.println("----------------------------------\n");

        Graph<DefaultEdge, DefaultEdge> lineGraph1 = createLineGraph(pathGraph);

        System.out.println("--- Line Graph of P3 ---");
        // Vertices of the line graph are the edges of the original graph
        System.out.println("Vertices (Original Edges): " + lineGraph1.vertexSet());
        // Edges of the line graph connect original edges that share a vertex
        System.out.println("Edges (Connections between original edges): " + lineGraph1.edgeSet());
        System.out.println("------------------------\n");
        // Expected: Vertices: [ab, bc], Edges: [(ab, bc)] - a single edge connecting the two original edges

        // --- Example 2: A Star Graph (K1,3) ---
        // A central vertex connected to 3 other vertices
        Graph<String, DefaultEdge> starGraph = new SimpleGraph<>(DefaultEdge.class);
        starGraph.addVertex("Center");
        starGraph.addVertex("Leaf1");
        starGraph.addVertex("Leaf2");
        starGraph.addVertex("Leaf3");
        DefaultEdge e1 = starGraph.addEdge("Center", "Leaf1");
        DefaultEdge e2 = starGraph.addEdge("Center", "Leaf2");
        DefaultEdge e3 = starGraph.addEdge("Center", "Leaf3");

        System.out.println("--- Original Star Graph (K1,3) ---");
        System.out.println("Vertices: " + starGraph.vertexSet());
        System.out.println("Edges: " + starGraph.edgeSet()); // Edges: [(Center,Leaf1), (Center,Leaf2), (Center,Leaf3)]
        System.out.println("------------------------------------\n");

        Graph<DefaultEdge, DefaultEdge> lineGraph2 = createLineGraph(starGraph);

        System.out.println("--- Line Graph of K1,3 ---");
        System.out.println("Vertices (Original Edges): " + lineGraph2.vertexSet());
        System.out.println("Edges (Connections between original edges): " + lineGraph2.edgeSet());
        System.out.println("--------------------------\n");
        // Expected: Vertices: [e1, e2, e3]. Edges: [(e1,e2), (e1,e3), (e2,e3)] - a K3 (triangle)
    }
}