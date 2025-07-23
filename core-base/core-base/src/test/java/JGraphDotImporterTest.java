import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.dot.DOTImporter;
import org.junit.jupiter.api.Test;


public class JGraphDotImporterTest<V,E> {


	@Test
	void testDotImporter() {

		InputStream dotStream = this.getClass().getResourceAsStream("decomposed_edges.dot");
		assertNotNull(dotStream);
		DOTImporter<String,DefaultEdge> dotImporter = new DOTImporter<>();
		dotImporter.setVertexFactory((label) -> {
			// Custom vertex factory logic (e.g., creating a custom vertex object)
			System.out.println("Creating vertex: " + label);
			return label; // Returning the label as the vertex ID in this example
		});
		Graph<String, DefaultEdge> graph = buildEmptySimpleGraph();
		try { 
			dotImporter.importGraph(graph, dotStream);
			Set<?> theSet = graph. edgeSet();
			theSet.forEach(e -> System.out.println(e));
		}	catch(Exception ex) { 
			ex.printStackTrace();
		}
	}

	private  Graph<String, DefaultEdge> buildEmptySimpleGraph()
	{
		return  new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
	}
	
}
