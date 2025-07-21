package org.nanotek.opencsv.metrics;

import javax.validation.constraints.NotNull;

import org.nanotek.Base;

public class InvertedVertexWeigth
implements Base<VertexWeigth> , VertexWeigthAcessor<Class<?>,Long>{

	private static final long serialVersionUID = 8153868311118135367L;
	
	Class<?> theVertex;
	
	Long theVertexWeigth;
	
	public InvertedVertexWeigth(@NotNull Class<?> theVertex,@NotNull Long theVertexWeigth) {
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
		int weightComparison = theVertexWeigth.compareTo(to.theVertexWeigth);
            return weightComparison; 
	}

	@Override
	public String toString() {
		return "VertexWeigth [theVertex=" + theVertex.getSimpleName() + ", theVertexWeigth=" + theVertexWeigth + "]";
	}
	
	// IMPORTANT: For objects used in Sets and Maps, or where natural order is defined,
	// it's good practice to override equals and hashCode based on the fields used in compareTo (or for uniqueness).
    // For VertexWeigth, it makes sense to base equals/hashCode primarily on 'theVertex' since the weight is derived from it.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexWeigth that = (VertexWeigth) o;
        return theVertex.equals(that.theVertex);
    }

    @Override
    public int hashCode() {
        return theVertex.hashCode();
    }
}