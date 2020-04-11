package org.nanotek.entities.metamodel.query.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Selection;

public  class AbstractBrainzSelection<Z> extends AbstractBrainzTupleElement<Z> implements Selection<Z> {
		
	protected Selection<Z> delegateSelector;

	
	public AbstractBrainzSelection() {
	}
	
	public AbstractBrainzSelection(Selection<Z> delegateSelector) {
		super(delegateSelector);
		this.delegateSelector = delegateSelector;
	}

	public AbstractBrainzSelection(BrainzCriteriaBuilder criteriaBuilder, Selection<Z> alias) {
		super(criteriaBuilder,alias);
		this.delegateSelector = alias;
	}

	public Selection<Z> getDelegateSelector() {
		return delegateSelector;
	}

	public void setDelegateSelector(Selection<Z> delegateSelector) {
		this.delegateSelector = delegateSelector;
	}


	public Selection<Z> alias(String name) {
		 this.delegateSelector = delegateSelector.alias(name);
		 return this;
	}

	public String getAlias() {
		return delegateSelector.getAlias();
	}

	public boolean isCompoundSelection() {
		return delegateSelector.isCompoundSelection();
	}

	public List<Selection<?>> getCompoundSelectionItems() {
		List<Selection<?>> l = new ArrayList<>();
		delegateSelector
			.getCompoundSelectionItems()
			.stream()
			.forEach(p->{
				if(! (p instanceof AbstractBrainzSelection))
					l.add(new AbstractBrainzSelection(brainzCriteriaBuilder,p));
				else 
					l.add(p);
			});
		return l;
	}

	
	
}
