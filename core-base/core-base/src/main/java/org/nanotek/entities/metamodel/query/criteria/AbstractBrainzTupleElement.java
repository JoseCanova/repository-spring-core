package org.nanotek.entities.metamodel.query.criteria;

import javax.persistence.TupleElement;

import org.nanotek.Id;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;

public abstract class AbstractBrainzTupleElement<Z> implements TupleElement<Z> ,Id<Class<Z>>{

	private TupleElement<Z> delegateTupleElement;
	
	public AbstractBrainzTupleElement() {
	}

	public AbstractBrainzTupleElement(TupleElement<Z> delegateTupleElement) {
		super();
		this.delegateTupleElement = delegateTupleElement;
	}

	public Class<? extends Z> getJavaType() {
		return delegateTupleElement.getJavaType();
	}

	public String getAlias() {
		return delegateTupleElement.getAlias();
	}

	
}
