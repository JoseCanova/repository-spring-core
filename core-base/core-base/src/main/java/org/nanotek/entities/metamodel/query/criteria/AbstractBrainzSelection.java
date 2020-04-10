package org.nanotek.entities.metamodel.query.criteria;

import java.util.List;

import javax.persistence.criteria.Selection;

public abstract class AbstractBrainzSelection<Z> extends BrainzTupleElement<Z> implements Selection<Z> {
		
	private Selection<Z> delegateSelector;
	
	public AbstractBrainzSelection() {
	}
	
	public AbstractBrainzSelection(Selection<Z> delegateSelector) {
		super();
		this.delegateSelector = delegateSelector;
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
		return delegateSelector.alias(name);
	}

	public String getAlias() {
		return delegateSelector.getAlias();
	}

	public boolean isCompoundSelection() {
		return delegateSelector.isCompoundSelection();
	}

	public List<Selection<?>> getCompoundSelectionItems() {
		return delegateSelector.getCompoundSelectionItems();
	}

	
	
}
