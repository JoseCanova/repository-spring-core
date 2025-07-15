import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.PlanarityTestingAlgorithm;
import org.jgrapht.alg.planar.BoyerMyrvoldPlanarityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

public class EmbeddingVsBFSIteration {

    public static void main(String[] args) {
        // --- 1. Create a simple planar graph (a square / C4) ---
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        DefaultEdge edgeAB = graph.addEdge("A", "B");
        DefaultEdge edgeBC = graph.addEdge("B", "C");
        DefaultEdge edgeCD = graph.addEdge("C", "D");
        DefaultEdge edgeDA = graph.addEdge("D", "A");

        System.out.println("--- Original Graph (C4) ---");
        System.out.println("Vertices: " + graph.vertexSet());
        System.out.println("Edges: " + graph.edgeSet());
        System.out.println("---------------------------\n");

        // --- 2. Perform Planarity Test and Get the Embedding ---
        BoyerMyrvoldPlanarityInspector<String, DefaultEdge> planarityInspector =
            new BoyerMyrvoldPlanarityInspector<>(graph);

        if (planarityInspector.isPlanar()) {
            System.out.println("Graph is planar. Retrieving embedding...");
            PlanarityTestingAlgorithm.Embedding<String, DefaultEdge> embedding = planarityInspector.getEmbedding();

            // --- 3. Embedding-Based "Iteration" (Edges Around a Specific Vertex) ---
            // Let's choose vertex "A" as our focus point.
            String startVertex = "A";

            System.out.println("\n--- Embedding-Based Edge Order Around Vertex '" + startVertex + "' ---");
            System.out.println("This shows the fixed cyclic order of edges around the vertex, defining faces.");
            try {
                // The getIncidentEdges method provides the edges in a cyclic order.
                // The order (CLOCKWISE/COUNTER_CLOCKWISE) is consistent for the embedding.
                // We'll iterate the list of edges.
                List<DefaultEdge> edgesAroundA = embedding.getEdgesAround(startVertex);
                System.out.print("Edges around '" + startVertex + "' (Clockwise): [");
                for (int i = 0; i < edgesAroundA.size(); i++) {
                    DefaultEdge edge = edgesAroundA.get(i);
                    System.out.print(edge);
                    if (i < edgesAroundA.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
                // You can get the other vertex of each edge to show the path:
                System.out.print("Vertices adjacent to '" + startVertex + "' in embedding order: [");
                for (int i = 0; i < edgesAroundA.size(); i++) {
                    DefaultEdge edge = edgesAroundA.get(i);
                    // Get the vertex on the other side of the edge from startVertex
                    String oppositeVertex = graph.getEdgeSource(edge).equals(startVertex) ? graph.getEdgeTarget(edge) : graph.getEdgeSource(edge);
                    System.out.print(oppositeVertex);
                    if (i < edgesAroundA.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");

            } catch (Exception e) {
                System.out.println("Could not get embedding details for vertex " + startVertex + ": " + e.getMessage());
            }
            System.out.println("------------------------------------------------------------------\n");


            // --- 4. Breadth-First Iterator "Printing" ---
            System.out.println("--- Breadth-First Iteration from Vertex '" + startVertex + "' ---");
            System.out.println("This shows a traversal order, typically layer by layer.");
            BreadthFirstIterator<String, DefaultEdge> bfsIterator =
                new BreadthFirstIterator<>(graph, startVertex);

            System.out.print("BFS Visit Order: [");
            int count = 0;
            while (bfsIterator.hasNext()) {
                String vertex = bfsIterator.next();
                System.out.print(vertex);
                if (bfsIterator.hasNext()) {
                    System.out.print(", ");
                }
                count++;
            }
            System.out.println("]");
            System.out.println("------------------------------------------\n");

        } else {
            System.out.println("The graph is not planar, cannot demonstrate embedding-based iteration.");
        }
    }
}