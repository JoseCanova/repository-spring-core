import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.dot.DOTImporter;
import org.junit.jupiter.api.Test;



public class JGraphDotImporterTest<V,E> {
	

	@Test
	void testDotImporter() {

		InputStream dotStream = this.getClass().getResourceAsStream("decomposed_edges.dot");
		assertNotNull(dotStream);
		DOTImporter<String,MyEdge> dotImporter = new DOTImporter<>();
		dotImporter.setVertexFactory((label) -> {
			// Custom vertex factory logic (e.g., creating a custom vertex object)
			System.out.println("Creating vertex: " + label);
			return label; // Returning the label as the vertex ID in this example
		});
		Graph<String, MyEdge> graph = buildEmptySimpleGraph();
		try { 
			dotImporter.importGraph(graph, dotStream);
			Set<?> theSet = graph. edgeSet();
			theSet.forEach(e -> System.out.println(e));
			List<String> theList = topologicalSort(graph);
			theList.stream().forEach(s -> System.err.println(s));
		}	catch(Exception ex) { 
			ex.printStackTrace();
		}
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
               String v = edge.getTarget().toString(); // Get the target vertex of the edge

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
