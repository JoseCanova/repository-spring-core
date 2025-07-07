package org.nanotek.opencsv.metrics;

public class VertexPair<X,Y> {

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
	public String toString() {
		return "VertexPair [source=" + source + ", target=" + target + "]";
	}

}
