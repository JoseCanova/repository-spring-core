package org.nanotek.entities.metamodel.query.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Selection;

public abstract class AbstractBrainzSelection<Z> implements Selection<Z> {
		
	private Selection<Z> delegateSelector;
	private CriteriaQuery<?> criteriaQuery;
	
	public AbstractBrainzSelection() {
	}
	
	public AbstractBrainzSelection(Selection<Z> delegateSelector) {
		super();
		this.delegateSelector = delegateSelector;
	}

	public AbstractBrainzSelection(CriteriaQuery<?> criteriaQuery, Selection<Z> alias) {
		this.criteriaQuery = criteriaQuery;
	}

	public Selection<Z> getDelegateSelector() {
		return delegateSelector;
	}

	public void setDelegateSelector(Selection<Z> delegateSelector) {
		this.delegateSelector = delegateSelector;
	}

	public Class<? extends Z> getJavaType() {
		return delegateSelector.getJavaType();
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
					l.add(new AbstractBrainzSelection(criteriaQuery,p) {});
				else 
					l.add(p);
			});
		return l;
	}

	
	
}
