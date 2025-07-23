import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.*;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

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
    public static void main(String[] args) throws Exception {
        String svgPath = "/home/jose/git/repository-spring-core/core-base/core-base/src/test/resources/entity_network_details.svg"; // 👈 Replace this with your file path
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        Document doc = factory.createDocument(svgPath, new FileReader(svgPath));

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

        // 🟨 Output DOT file
        try (PrintWriter writer = new PrintWriter("/home/jose/decomposed_edges.dot")) {
            writer.println("digraph G {");

            for (Vertex v : vertices.values()) {
                writer.printf("  %s [label=\"%s\"];\n", v.id, v.label);
            }

            for (Edge e : edges) {
                writer.printf("  %s -> %s [label=\"%s\"];\n", e.from, e.to, e.label);
            }

            writer.println("}");
        }

        System.out.println("✅ DOT file created: decomposed_edges.dot");
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
