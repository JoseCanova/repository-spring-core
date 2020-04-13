package org.nanotek.entities.metamodel.query.criteria;

import java.util.Set;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.FetchParent;
import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SingularAttribute;

public abstract class AbstractBrainzFetch<Z, X> implements Fetch<Z, X> {

	private Fetch<Z,X> delegateFetch;
	
	public AbstractBrainzFetch() {
	}
	
	public AbstractBrainzFetch(Fetch<Z, X> delegateFetch) {
		super();
		this.delegateFetch = delegateFetch;
	}

	public Attribute<? super Z, ?> getAttribute() {
		return delegateFetch.getAttribute();
	}

	public Set<Fetch<X, ?>> getFetches() {
		return delegateFetch.getFetches();
	}

	public FetchParent<?, Z> getParent() {
		return delegateFetch.getParent();
	}

	public JoinType getJoinType() {
		return delegateFetch.getJoinType();
	}

	public <Y> Fetch<X, Y> fetch(SingularAttribute<? super X, Y> attribute) {
		return delegateFetch.fetch(attribute);
	}

	public <Y> Fetch<X, Y> fetch(SingularAttribute<? super X, Y> attribute, JoinType jt) {
		return delegateFetch.fetch(attribute, jt);
	}

	public <Y> Fetch<X, Y> fetch(PluralAttribute<? super X, ?, Y> attribute) {
		return delegateFetch.fetch(attribute);
	}

	public <Y> Fetch<X, Y> fetch(PluralAttribute<? super X, ?, Y> attribute, JoinType jt) {
		return delegateFetch.fetch(attribute, jt);
	}

	@SuppressWarnings("hiding")
	public <X, Y> Fetch<X, Y> fetch(String attributeName) {
		return delegateFetch.fetch(attributeName);
	}

	@SuppressWarnings("hiding")
	public <X, Y> Fetch<X, Y> fetch(String attributeName, JoinType jt) {
		return delegateFetch.fetch(attributeName, jt);
	}

}
