package org.nanotek.opencsv.metrics;

import java.io.Serializable;

import org.nanotek.BaseEntity;

public class VertexDistance<K extends BaseEntity<K,ID>, ID extends Serializable> implements BaseEntity<K, ID> {
    
	ID id; 
	
	VertexPair<K, ID> vertexPair;
	
	Integer distance;
	
	VertexDistance(ID id) {
		this.id = id;
	}
	
	
	public VertexDistance(ID id, VertexPair<K, ID> vertexPair) {
		this.id = id;
		this.vertexPair = vertexPair;
	}
	
	public VertexDistance(ID id, VertexPair<K, ID> vertexPair, Integer distance) {
		this.id = id;
		this.vertexPair = vertexPair;
		this.distance = distance;
	}
	
	public VertexPair<K, ID> getVertexPair() {
		return vertexPair;
	}
	
	@Override
	public ID getId() {
		return id;
	}
	

	public Integer getDistance() {
		return distance;
	}

}
