package org.nanotek.opencsv.metrics;

import org.nanotek.Base;

public class VertexDistance<X, Y> 
implements Base<VertexDistance<X, Y> >
{
    
	VertexPair<X, Y> vertexPair;
	
	Double distance;
	
	public VertexDistance(Double distance2, VertexPair<X, Y> vertexPair) {
		this.distance = distance2;
		this.vertexPair = vertexPair;
	}
	
	public VertexPair<X,Y> getVertexPair() {
		return vertexPair;
	}
	
	public Double getDistance() {
		return distance;
	}
	

}
