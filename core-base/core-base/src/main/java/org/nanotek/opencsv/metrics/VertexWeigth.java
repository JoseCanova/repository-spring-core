package org.nanotek.opencsv.metrics;

import javax.validation.constraints.NotNull;

import org.nanotek.Base;

public class VertexWeigth
implements Base<VertexWeigth> , VertexWeigthAcessor<Class<?>,Long>{

	private static final long serialVersionUID = 8153868311118135367L;
	
	Class<?> theVertex;
	
	Long theVertexWeigth;
	
	public VertexWeigth(@NotNull Class<?> theVertex,@NotNull Long theVertexWeigth) {
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
		return theVertexWeigth.compareTo(to.theVertexWeigth) * -1;
	}

	@Override
	public String toString() {
		return "VertexWeigth [theVertex=" + theVertex.getSimpleName() + ", theVertexWeigth=" + theVertexWeigth + "]";
	}
	
	
}
