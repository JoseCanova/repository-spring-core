package org.nanotek.opencsv.metrics;

import org.nanotek.Base;

public class VertexWeigth
implements Base<VertexWeigth> , VertexWeigthAcessor<Class<?>,Long>{

	private static final long serialVersionUID = 8153868311118135367L;
	
	Class<?> theVertex;
	
	Long theVertexWeigth;
	
	public VertexWeigth(Class<?> theVertex,Long theVertexWeigth) {
		this.theVertex = theVertex;
		this.theVertexWeigth = theVertexWeigth;
	}

	@Override
	public Class<?> theVertex() {
		return theVertex;
	}

	@Override
	public Long theVertexWeigth() {
		return theVertexWeigth;
	}
	
	@Override
	public int compareTo(VertexWeigth to) {
		return theVertexWeigth.compareTo(to.theVertexWeigth);
	}

	@Override
	public String toString() {
		return "VertexWeigth [theVertex=" + theVertex.getSimpleName() + ", theVertexWeigth=" + theVertexWeigth + "]";
	}
	
	
}
