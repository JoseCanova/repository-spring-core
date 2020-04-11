package org.nanotek.entities.metamodel.query.criteria;

import javax.persistence.TupleElement;

import org.nanotek.Id;

public abstract class AbstractBrainzTupleElement<Z> implements TupleElement<Z> ,Id<Class<? extends Z>>{

	protected  TupleElement<Z> delegateTupleElement;
	
	protected  BrainzCriteriaBuilder  brainzCriteriaBuilder;
	
	public AbstractBrainzTupleElement() {
	}

	public AbstractBrainzTupleElement(TupleElement<Z> delegateTupleElement) {
		super();
		this.delegateTupleElement = delegateTupleElement;
	}
	

	public AbstractBrainzTupleElement(BrainzCriteriaBuilder  brainzCriteriaBuilder , TupleElement<Z> delegateTupleElement) {
		super();
		this.delegateTupleElement = delegateTupleElement;
		this.brainzCriteriaBuilder = brainzCriteriaBuilder;
	}

	public Class<? extends Z> getJavaType() {
		return delegateTupleElement.getJavaType();
	}

	public String getAlias() {
		return delegateTupleElement.getAlias();
	}
	
	@Override
	public Class<? extends Z> getId() {
		return delegateTupleElement.getJavaType();
	}
}
