package org.nanotek.opencsv;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.nanotek.BaseEntity;

public class CsvGraphTraversalListener 
implements TraversalListener<Class<? extends BaseEntity<?,?>>, PriorityEdge> {
	
	/**
	 * The live element graph used to traverse the entities.
	 */
	private Graph<Class<? extends BaseEntity<?, ?>>, PriorityEdge> graph;
	
	public CsvGraphTraversalListener() {}
	
	
	@SuppressWarnings("unchecked")
	public CsvGraphTraversalListener(Graph<Class<? extends BaseEntity>, ?> entityDirectedGraph) {
		this.graph = graph.getClass().cast(entityDirectedGraph);
	}

	public void rootInitialization(Class<? extends BaseEntity<?, ?>> root) {
	}
	
	@Override
	public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {
	}

	@Override
	public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {
	}

	@Override
	public void edgeTraversed(EdgeTraversalEvent<PriorityEdge> arg0) {
	}

	@Override
	public void vertexFinished(VertexTraversalEvent<Class<? extends BaseEntity<?, ?>>> arg0) {
	}

	@Override
	public void vertexTraversed(VertexTraversalEvent<Class<? extends BaseEntity<?, ?>>> arg0) {
	}
	
}
