package org.nanotek.opencsv.metrics;

import org.nanotek.Base;

public class VertexPair<X,Y> 
implements Base<VertexPair<X,Y>> {

	private X source;
	private Y target;

	public VertexPair(X source, Y target) {
		this.source = source;
		this.target = target;
	}

	public X getSource() {
		return source;
	}

	public void setSource(X source) {
		this.source = source;
	}

	public Y getTarget() {
		return target;
	}

	public void setTarget(Y target) {
		this.target = target;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof VertexPair) {
			VertexPair<X,Y> vp = VertexPair.class.cast(obj);
			return this.compareTo(vp) == 0;
		}
		return false;
		
	}
	
	@Override
	public int hashCode() {
		return this.withUUID().toString().hashCode();
	}
	
	@Override
	public String toString() {
		return "VertexPair [source=" + source + ", target=" + target + "]";
	}

}
