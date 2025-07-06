package org.nanotek.entities.metamodel;

import org.jgrapht.graph.DefaultWeightedEdge;

public class MetaModelEdge extends DefaultWeightedEdge{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4545976841282043463L; 
	
	

	public BrainzEntityMetaModel<?, ?>  getSource()
	{
		return (BrainzEntityMetaModel<?, ?>) super.getSource();
	}

	/**
	 * Retrieves the target of this edge. This is protected, for use by subclasses only (e.g. for
	 * implementing toString).
	 *
	 * @return target of this edge
	 */
	public BrainzEntityMetaModel<?, ?>  getTarget()
	{
		return (BrainzEntityMetaModel<?, ?>) super.getTarget();
	}

	public <X> String getWeighted(X x) {
		return String.valueOf(super.getWeight());
	}

}
