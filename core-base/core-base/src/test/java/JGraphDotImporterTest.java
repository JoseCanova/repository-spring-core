import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;
import java.io.Reader;

import org.jgrapht.Graph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.nio.dot.DOTImporter;
import org.junit.jupiter.api.Test;


public class JGraphDotImporterTest<V,E> {

	
	@Test
	void testDotImporter() {
		
		InputStream dotStream = this.getClass().getResourceAsStream("decomposed_edges.dot");
		assertNotNull(dotStream);
		DOTImporter<V,E> dotImporter = new DOTImporter<>();
		Graph<V,E> graph = buildEmptySimpleGraph();
		dotImporter.importGraph(graph, dotStream);
	}

	private  Graph<V,E> buildEmptySimpleGraph()
	{
		return GraphTypeBuilder
				.<V,E>undirected().allowingMultipleEdges(true)
				.allowingSelfLoops(true).weighted(false).buildGraph();
	}
	
}
