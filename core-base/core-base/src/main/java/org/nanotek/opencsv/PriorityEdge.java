package org.nanotek.opencsv;

import org.jgrapht.graph.DefaultEdge;

public class PriorityEdge extends DefaultEdge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public PriorityEdge() {}
	
	@Override
	public Object getSource() {
		return super.getSource();
	}
	
	@Override
	protected Object getTarget() {
		return super.getTarget();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!PriorityEdge.class.isInstance(obj))
			return false;
		PriorityEdge e1 = PriorityEdge.class.cast(obj);
		return super.getSource().equals(e1.getSource()) && super.getTarget().equals(e1.getTarget());
	}
	
	
}