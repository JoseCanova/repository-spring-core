import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

class Vertex {
    String id;
    String label;

    Vertex(String id, String label) {
        this.id = id;
        this.label = label;
    }
}

class Edge {
    String from, to, label;

    Edge(String from, String to, String label) {
        this.from = from;
        this.to = to;
        this.label = label;
    }
}

public class SvgDirectedGraph {
	
	
	private static final String svgLocation = "file:///home/jose/Documents/research/svg-music-brainz/";
	
	
    public static void main(String[] args) throws Exception {
    	
    	Stream <Path> svgFiles = Files.list(Paths.get(URI.create(svgLocation)));
    	
    	svgFiles
    	.forEach(f -> {
    		
    		try {
    		    convertSvgToDotNotation(f);
    		}catch(Exception ex){
    			throw new RuntimeException(ex);
    		}
    	});
    	
//        String svgPath = "/home/jose/git/repository-spring-core/core-base/core-base/src/test/resources/entity_network_details.svg"; // 👈 Replace this with your file path
//        String parser = XMLResourceDescriptor.getXMLParserClassName();
//        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
//        Document doc = factory.createDocument(svgPath, new FileReader(svgPath));
//
//        Map<String, Vertex> vertices = new LinkedHashMap<>();
//        List<Edge> edges = new ArrayList<>();
//
//        // 🟦 Extract vertices from <g class="node">
//        NodeList groupNodes = doc.getElementsByTagName("g");
//        for (int i = 0; i < groupNodes.getLength(); i++) {
//            Element group = (Element) groupNodes.item(i);
//            if ("node".equals(group.getAttribute("class"))) {
//                NodeList titleNodes = group.getElementsByTagName("title");
//                if (titleNodes.getLength() > 0) {
//                    String label = titleNodes.item(0).getTextContent().trim();
//                    String id = sanitize(label);
//                    vertices.put(id, new Vertex(id, label));
//                }
//            }
//        }
//
//        // 🟥 Extract edges from <g class="edge">
//        for (int i = 0; i < groupNodes.getLength(); i++) {
//            Element group = (Element) groupNodes.item(i);
//            if ("edge".equals(group.getAttribute("class"))) {
//                NodeList titleNodes = group.getElementsByTagName("title");
//                if (titleNodes.getLength() > 0) {
//                    String title = titleNodes.item(0).getTextContent().trim();
//
//                    // Parse title: format = source:label -> target:label
//                    if (title.contains("->")) {
//                        String[] sides = title.split("->");
//                        String left = sides[0];     // target-side (in our logic)
//                        String right = sides[1];    // source-side
//
//                        String from = extractEntity(right);  // annotation
//                        String to = extractEntity(left);     // instrument_annotation
//                        String edgeLabel = extractLabel(right); // id
//
//                        String fromId = sanitize(from);
//                        String toId = sanitize(to);
//
//                        edges.add(new Edge(fromId, toId, edgeLabel));
//                    }
//                }
//            }
//        }
//
//        // 🟨 Output DOT file
//        try (PrintWriter writer = new PrintWriter("/home/jose/decomposed_edges.dot")) {
//            writer.println("digraph G {");
//
//            for (Vertex v : vertices.values()) {
//                writer.printf("  %s [label=\"%s\"];\n", v.id, v.label);
//            }
//
//            for (Edge e : edges) {
//                writer.printf("  %s -> %s [label=\"%s\"];\n", e.from, e.to, e.label);
//            }
//
//            writer.println("}");
//        }
//
//        System.out.println("✅ DOT file created: decomposed_edges.dot");
    }

    private static void convertSvgToDotNotation(Path f) throws Exception {
    	
//    	 String svgPath = "/home/jose/git/repository-spring-core/core-base/core-base/src/test/resources/entity_network_details.svg"; // 👈 Replace this with your file path
         String parser = XMLResourceDescriptor.getXMLParserClassName();
         SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
         
         Document doc = factory.createDocument(f.toUri().toString(), Files.newBufferedReader(f));
         
         Map<String, Vertex> vertices = new LinkedHashMap<>();
         List<Edge> edges = new ArrayList<>();

         // 🟦 Extract vertices from <g class="node">
         NodeList groupNodes = doc.getElementsByTagName("g");
         for (int i = 0; i < groupNodes.getLength(); i++) {
             Element group = (Element) groupNodes.item(i);
             if ("node".equals(group.getAttribute("class"))) {
                 NodeList titleNodes = group.getElementsByTagName("title");
                 if (titleNodes.getLength() > 0) {
                     String label = titleNodes.item(0).getTextContent().trim();
                     String id = sanitize(label);
                     vertices.put(id, new Vertex(id, label));
                 }
             }
         }

         // 🟥 Extract edges from <g class="edge">
         for (int i = 0; i < groupNodes.getLength(); i++) {
             Element group = (Element) groupNodes.item(i);
             if ("edge".equals(group.getAttribute("class"))) {
                 NodeList titleNodes = group.getElementsByTagName("title");
                 if (titleNodes.getLength() > 0) {
                     String title = titleNodes.item(0).getTextContent().trim();

                     // Parse title: format = source:label -> target:label
                     if (title.contains("->")) {
                         String[] sides = title.split("->");
                         String left = sides[0];     // target-side (in our logic)
                         String right = sides[1];    // source-side

                         String from = extractEntity(right);  // annotation
                         String to = extractEntity(left);     // instrument_annotation
                         String edgeLabel = extractLabel(right); // id

                         String fromId = sanitize(from);
                         String toId = sanitize(to);

                         edges.add(new Edge(fromId, toId, edgeLabel));
                     }
                 }
             }
         }
         
         String decomposedFile = f.getFileName().toString().replaceAll("svg", "dot");

         // 🟨 Output DOT file
         try (PrintWriter writer = new PrintWriter(f.getParent().toString().concat("/").concat(decomposedFile))) {
             writer.println("digraph G {");

             for (Vertex v : vertices.values()) {
                 writer.printf("  %s [label=\"%s\"];\n", v.id, v.label);
             }

             for (Edge e : edges) {
                 writer.printf("  %s -> %s [label=\"%s\"];\n", e.from, e.to, e.label);
             }

             writer.println("}");
         }

         System.out.println("✅ DOT file created: " .concat(decomposedFile));		
	}

	// 🔧 Extract base entity from "entity:label"
    private static String extractEntity(String text) {
        String[] parts = text.split(":");
        return parts.length > 0 ? parts[0].trim() : text.trim();
    }

    // 🔧 Extract field label from "entity:label"
    private static String extractLabel(String text) {
        String[] parts = text.split(":");
        return parts.length > 1 ? parts[1].trim() : "";
    }

    // 🔧 Sanitize ID for Graphviz
    private static String sanitize(String raw) {
        return raw.replaceAll("[^a-zA-Z0-9_]", "_");
    }
}
