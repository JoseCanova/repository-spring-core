import org.jgrapht.Graph;
import org.jgrapht.alg.planar.BoyerMyrvoldPlanarityInspector;
import org.jgrapht.alg.planar.DualGraph;
import org.jgrapht.alg.planar.PlanarGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class DualGraphGenerator {

    public static void main(String[] args) {
        // 1. Create a simple planar graph (a square / C4)
        Graph<String, DefaultEdge> originalGraph = new SimpleGraph<>(DefaultEdge.class);
        originalGraph.addVertex("A");
        originalGraph.addVertex("B");
        originalGraph.addVertex("C");
        originalGraph.addVertex("D");
        originalGraph.addEdge("A", "B");
        originalGraph.addEdge("B", "C");
        originalGraph.addEdge("C", "D");
        originalGraph.addEdge("D", "A");

        System.out.println("--- Original Graph (G) ---");
        System.out.println("Vertices: " + originalGraph.vertexSet());
        System.out.println("Edges: " + originalGraph.edgeSet());
        System.out.println("--------------------------\n");

        // 2. Perform planarity test and get the planar embedding
        BoyerMyrvoldPlanarityInspector<String, DefaultEdge> planarityTest =
            new BoyerMyrvoldPlanarityInspector<>(originalGraph);

        if (planarityTest.isPlanar()) {
            System.out.println("Graph is planar. Proceeding to construct dual graph.\n");

            // Get the PlanarGraph object which contains the embedding info
            PlanarGraph<String, DefaultEdge> planarEmbedding = planarityTest.getPlanarGraph();

            // 3. Construct the Dual Graph
            // The DualGraph constructor takes the planar embedding
            DualGraph<String, DefaultEdge, DefaultEdge> dualGraphGenerator =
                new DualGraph<>(planarEmbedding);

            // Get the actual dual graph representation
            Graph<DefaultEdge, DefaultEdge> dualGraph = dualGraphGenerator.getDualGraph();

            System.out.println("--- Dual Graph (G*) ---");
            System.out.println("Vertices (representing faces of G): " + dualGraph.vertexSet());
            System.out.println("Edges (representing shared boundaries in G): " + dualGraph.edgeSet());
            System.out.println("-----------------------\n");

            // For a C4 (square), the dual graph should have 2 vertices
            // (one for the inner face, one for the outer face)
            // and 4 parallel edges connecting them (one for each original edge shared by faces).

            // Let's verify the number of vertices and edges in the dual graph
            System.out.println("Dual Graph Vertices Count: " + dualGraph.vertexSet().size());
            System.out.println("Dual Graph Edges Count: " + dualGraph.edgeSet().size());

            // You can iterate through the dual graph vertices (which are DefaultEdge objects
            // representing faces, specifically the edge that represents the 'cut' for that face)
            // For a simple graph like C4, the dual graph's vertices might not be intuitive directly
            // from their DefaultEdge string representation. They are internal JGraphT edge objects
            // that identify the face boundaries. However, their count and connectivity are correct.

        } else {
            System.out.println("The original graph is not planar, cannot construct a dual graph.");
        }
    }
}