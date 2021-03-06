package org.nanotek.entities.metamodel.query.criteria;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;

public class AbstractBrainzOrder implements Order {
	
	private Order delegateOrder; 
	
	private BrainzCriteriaBuilder criteriaBuilder; 

	public AbstractBrainzOrder() {
	}
	
	public AbstractBrainzOrder(Order delegateOrder) {
		super();
		this.delegateOrder = delegateOrder;
	}

	public AbstractBrainzOrder(BrainzCriteriaBuilder criteriaBuilder , Order delegateOrder) {
		super();
		this.criteriaBuilder = criteriaBuilder;
		this.delegateOrder = delegateOrder;
	}
	
	@Override
	public Order reverse() {
		return (delegateOrder = delegateOrder.reverse());
	}

	@Override
	public boolean isAscending() {
		return delegateOrder.isAscending();
	}

	@Override
	public Expression<?> getExpression() {
		return delegateOrder.getExpression();
	}

}
