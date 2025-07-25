package org.nanotek.opencsv;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.PlanarityTestingAlgorithm;
import org.jgrapht.alg.planar.BoyerMyrvoldPlanarityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.BaseEntity;
import org.nanotek.beans.entity.Artist;
import org.nanotek.graph.brainz.MusicBrainzKnowledgeGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BrainzGraphEmbeddingVsBFSIteration {
	
	@Autowired
	MusicBrainzKnowledgeGraph brainzGraphModel;
	
	@Test
    void main() {
        // --- 1. Create a simple planar graph (a square / C4) ---
        Graph<Class<? extends BaseEntity>, PriorityEdge> theGraph = (Graph<Class<? extends BaseEntity>, PriorityEdge>) brainzGraphModel.getEntityDirectedGraph();
        System.out.println("--- Original Graph (C4) ---");
        System.out.println("Vertices: " + theGraph.vertexSet());
        System.out.println("Edges: " + theGraph.edgeSet());
        System.out.println("---------------------------\n");

        // --- 2. Perform Planarity Test and Get the Embedding ---
        BoyerMyrvoldPlanarityInspector<Class<? extends BaseEntity>, PriorityEdge> planarityInspector =
            new BoyerMyrvoldPlanarityInspector<>(theGraph);

        if (planarityInspector.isPlanar()) {
            System.out.println("Graph is planar. Retrieving embedding...");
            PlanarityTestingAlgorithm.Embedding<Class<? extends BaseEntity>, PriorityEdge> embedding = planarityInspector.getEmbedding();

            // --- 3. Embedding-Based "Iteration" (Edges Around a Specific Vertex) ---
            // Let's choose vertex "A" as our focus point.
            Class<? extends BaseEntity> startVertex = Artist.class;

            System.out.println("\n--- Embedding-Based Edge Order Around Vertex '" + startVertex + "' ---");
            System.out.println("This shows the fixed cyclic order of edges around the vertex, defining faces.");
            try {
                // The getIncidentEdges method provides the edges in a cyclic order.
                // The order (CLOCKWISE/COUNTER_CLOCKWISE) is consistent for the embedding.
                // We'll iterate the list of edges.
                List<PriorityEdge> edgesAroundA = embedding.getEdgesAround(startVertex);
                System.out.print("Edges around '" + startVertex.getSimpleName() + "' (Clockwise): [");
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
                    PriorityEdge edge = edgesAroundA.get(i);
                    // Get the vertex on the other side of the edge from startVertex
                    Class<? extends BaseEntity> oppositeVertex = theGraph.getEdgeSource(edge).equals(startVertex) ? theGraph.getEdgeTarget(edge) : theGraph.getEdgeSource(edge);
                    System.out.print(oppositeVertex.getSimpleName());
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
            BreadthFirstIterator<Class<? extends BaseEntity>, PriorityEdge> bfsIterator =
                new BreadthFirstIterator<>(theGraph, startVertex);

            System.out.print("BFS Visit Order: [");
            int count = 0;
            while (bfsIterator.hasNext()) {
            	Class<? extends BaseEntity> vertex = bfsIterator.next();
                System.out.print(vertex.getSimpleName());
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