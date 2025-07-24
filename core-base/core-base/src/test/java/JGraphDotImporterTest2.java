import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.nio.dot.DOTImporter;
import org.junit.jupiter.api.Test;

// NOTE: MyEdge class is assumed to be defined elsewhere and compatible with JGraphT.
// For example:
// class MyEdge extends DefaultEdge {
//     // You might add custom properties or methods here if needed
//     // For simple cases, DefaultEdge could also be used directly.
//     private String label;
//     public MyEdge(Object source, Object target, String label) {
//         super(); // Call DefaultEdge constructor if MyEdge extends DefaultEdge
//         // this.source = source; // DefaultEdge handles source/target internally
//         // this.target = target;
//         this.label = label;
//     }
//     public MyEdge() {
//         super();
//     }
//     public String getLabel() { return label; }
//     public void setLabel(String label) { this.label = label; }
//     @Override
//     public String toString() {
//         // This is important for printing edges as expected
//         return "(" + getSource() + " : " + getTarget() + ") - " + label;
//     }
// }


public class JGraphDotImporterTest2<V,E> {
	
	@Test
	void testDotImporter() {

		// Ensure 'decomposed_edges.dot' is in your test resources folder
		InputStream dotStream = this.getClass().getResourceAsStream("decomposed_edges.dot");
		assertNotNull(dotStream, "decomposed_edges.dot not found in resources. Please ensure the file exists.");
		
		DOTImporter<String,MyEdge> dotImporter = new DOTImporter<>();
		dotImporter.setVertexFactory((label) -> {
			// Custom vertex factory logic (e.g., creating a custom vertex object)
			System.out.println("Creating vertex: " + label);
			return label; // Returning the label as the vertex ID in this example
		});
		
		Graph<String, MyEdge> graph = buildEmptySimpleGraph();
		
		try { 
			dotImporter.importGraph(graph, dotStream);
			
			System.out.println("\n--- Edges from single import (decomposed_edges.dot) ---");
			Set<?> theSet = graph.edgeSet();
			theSet.forEach(e -> System.out.println(e));
			
			System.out.println("\n--- Topological Sort from single import ---");
			List<String> theList = topologicalSort(graph);
			theList.stream().forEach(s -> System.err.println(s));
			
		}	catch(Exception ex) { 
			System.err.println("Error during single DOT import test: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Test
    void testDotImporterFromFolder() {
        // 1. Define the folder containing DOT files
        // IMPORTANT: Replace this with an actual path on your system where you have .dot files
        // For example: "src/test/resources/dot_files" if you have a subfolder.
        // Make sure this folder exists and contains .dot files for the test to pass.
        String dotFilesFolderPath = "/home/jose/dot_graphs"; 

        File folder = new File(dotFilesFolderPath);
        assertTrue(folder.exists() && folder.isDirectory(), 
            "ERROR: DOT files folder must exist and be a directory: " + dotFilesFolderPath);

        // Filter for .dot files
        File[] dotFiles = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".dot");
            }
        });

        assertNotNull(dotFiles, "ERROR: No DOT files array returned from directory: " + dotFilesFolderPath);
        assertTrue(dotFiles.length > 0, "ERROR: No .dot files found in the directory: " + dotFilesFolderPath);

        List<Graph<String, MyEdge>> importedGraphs = new ArrayList<>();

        // 2. Iterate through each .dot file
        for (File dotFile : dotFiles) {
            System.out.println("\n--- Importing graph from: " + dotFile.getName() + " ---");
            try (InputStream dotStream = new FileInputStream(dotFile)) {
                DOTImporter<String, MyEdge> dotImporter = new DOTImporter<>();
                dotImporter.setVertexFactory(label -> {
                    // Custom vertex factory logic (e.g., creating a custom vertex object)
                    // For this example, we just use the label as the vertex ID
                    // System.out.println("  Creating vertex for " + dotFile.getName() + ": " + label);
                    return label; 
                });

                // Create a new empty graph for each file to ensure isolation
                Graph<String, MyEdge> graph = buildEmptySimpleGraph(); 

                dotImporter.importGraph(graph, dotStream);
                importedGraphs.add(graph);

                System.out.println("  ✅ Successfully imported graph from: " + dotFile.getName());
                System.out.println("    Vertices: " + graph.vertexSet().size() + ", Edges: " + graph.edgeSet().size());

                // Optionally, perform topological sort and print for each imported graph
                try {
                    List<String> sortedList = topologicalSort(graph);
                    System.out.println("  Topological Sort for " + dotFile.getName() + ": " + sortedList);
                } catch (IllegalArgumentException e) {
                    System.err.println("  ⚠️ Warning: Graph from " + dotFile.getName() + " contains a cycle. " + e.getMessage());
                }

            } catch (Exception ex) {
                System.err.println("  ❌ Error importing " + dotFile.getName() + ": " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        // 3. Assertions for the collected graphs
        assertFalse(importedGraphs.isEmpty(), "No graphs were imported into the list. Check folder path and file contents.");
        System.out.println("\n--- Total graphs imported: " + importedGraphs.size() + " ---");

        // You can add more specific assertions here, e.g., check the size of the list,
        // or properties of specific graphs if you know their expected structure.
        assertTrue(importedGraphs.size() > 0, "The list of imported graphs should not be empty.");
    }

	private  DefaultDirectedGraph<String, MyEdge> buildEmptySimpleGraph()
	{
		return  new DefaultDirectedGraph<String, MyEdge>(MyEdge.class);
	}
	
	  /**
     * Performs a topological sort on the given JGraphT graph using Kahn's algorithm.
     *
     * @param graph The JGraphT graph to sort. It must be a Directed Acyclic Graph (DAG).
     * @return A list of vertices in topological order.
     * @throws IllegalArgumentException if the graph contains a cycle.
     */
    public List<String> topologicalSort(Graph<String, MyEdge> graph) {
        // 1. Initialize in-degrees for all vertices
        Map<String, Integer> inDegree = new HashMap<>();
        for (String vertex : graph.vertexSet()) {
            inDegree.put(vertex, graph.inDegreeOf(vertex));
        }

        // 2. Initialize a queue with all vertices having an in-degree of zero
        Queue<String> queue = new LinkedList<>();
        for (String vertex : graph.vertexSet()) {
            if (inDegree.get(vertex) == 0) {
                queue.offer(vertex);
            }
        }

        // Use a list to store the sorted vertices
        List<String> sortedList = new ArrayList<>();
        int visitedVerticesCount = 0;

        // 3. Process vertices
        while (!queue.isEmpty()) {
            String u = queue.poll();
            sortedList.add(u);
            visitedVerticesCount++;

            // For each neighbor v of u (i.e., for each edge u -> v)
            for (MyEdge edge : graph.outgoingEdgesOf(u)) {
               // Correctly get target vertex using edge and graph, or custom edge method
               // If MyEdge stores target directly:
               String v = edge.getTarget().toString(); 
               // If MyEdge is a DefaultEdge or similar and you need JGraphT's help:
               // String v = Graphs.getTargetVertex(graph, edge); 

                // Decrement in-degree of v
                inDegree.put(v, inDegree.get(v) - 1);

                // If in-degree of v becomes zero, add it to the queue
                if (inDegree.get(v) == 0) {
                    queue.offer(v);
                }
            }
        }

        // 4. Check for cycles
        if (visitedVerticesCount != graph.vertexSet().size()) {
            throw new IllegalArgumentException("Graph contains a cycle, topological sort is not possible.");
        }

        return sortedList;
    }
	
}