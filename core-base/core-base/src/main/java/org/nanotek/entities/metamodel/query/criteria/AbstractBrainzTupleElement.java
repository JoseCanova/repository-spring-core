package org.nanotek.entities.metamodel.query.criteria;

import org.hibernate.query.criteria.internal.TupleElementImplementor;
import org.hibernate.query.criteria.internal.expression.AbstractTupleElement;

public  class AbstractBrainzTupleElement
<Z,Y extends TupleElementImplementor<Z>> 
extends AbstractTupleElement<Z> 
implements BrainzTupleImplementor<Z,Y>{
	
	
	protected Class<Z> Id;
	
	protected  Y delegateTupleElement;
	
	protected  BrainzCriteriaBuilder  brainzCriteriaBuilder;
	

	public AbstractBrainzTupleElement(BrainzCriteriaBuilder  brainzCriteriaBuilder , Y tupleImplementor) {
		super(brainzCriteriaBuilder.getDelegateCriteriaBuilder(),withId(tupleImplementor.getJavaType()));
		this.delegateTupleElement = tupleImplementor;
		this.brainzCriteriaBuilder = brainzCriteriaBuilder;
	}

	private static <Y extends X,X>  Class<Y> withId(Class<? extends X> javaType) {
		return (Class<Y>) javaType;
	}

	public String getAlias() {
		return delegateTupleElement.getAlias();
	}
	
	@Override
	public Class<Z> getId() {
		return withId(delegateTupleElement.getJavaType());
	}
	
	@Override
	public Y getDelegateTupleElement() {
		return delegateTupleElement;
	}
}
