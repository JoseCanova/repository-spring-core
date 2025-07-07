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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof VertexDistance) {
			VertexDistance<X,Y> vp = VertexDistance.class.cast(obj);
			return this.compareTo(vp) == 0;
		}
		return false;
		
	}
	
	@Override
	public int hashCode() {
		return this.withUUID().toString().hashCode();
	}

}
