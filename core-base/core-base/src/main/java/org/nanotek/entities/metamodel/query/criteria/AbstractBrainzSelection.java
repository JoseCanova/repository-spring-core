package org.nanotek.entities.metamodel.query.criteria;

import java.util.List;

import javax.persistence.criteria.Selection;

import org.hibernate.query.criteria.internal.SelectionImplementor;
import org.hibernate.query.criteria.internal.ValueHandlerFactory.ValueHandler;

public  class AbstractBrainzSelection<Z,Y extends SelectionImplementor<Z>> 
extends AbstractBrainzTupleElement<Z,Y> implements BrainzSelectionImplementor<Z,Y> {
		

	private AbstractBrainzSelection(Y delegateSelector) {
		super(null , null);
		this.delegateTupleElement = delegateSelector;
	}
	
	public AbstractBrainzSelection(BrainzCriteriaBuilder criteriaBuilder, Y delegateSelector) {
		super(criteriaBuilder,delegateSelector);
	}

	public Selection<Z> getDelegateSelector() {
		return delegateTupleElement;
	}

	public void setDelegateSelector(Y delegateSelector) {
		this.delegateTupleElement = delegateSelector;
	}


	public Selection<Z> alias(String name) {
		 return delegateTupleElement.alias(name);
	}

	public String getAlias() {
		return delegateTupleElement.getAlias();
	}

	public boolean isCompoundSelection() {
		return delegateTupleElement.isCompoundSelection();
	}

	public List<Selection<?>> getCompoundSelectionItems() {
		return delegateTupleElement.getCompoundSelectionItems();
	}

	@Override
	public List<ValueHandler> getValueHandlers() {
		return delegateTupleElement.getValueHandlers();
	}

	
	
}
