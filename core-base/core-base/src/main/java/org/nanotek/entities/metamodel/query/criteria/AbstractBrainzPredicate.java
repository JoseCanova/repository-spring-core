package org.nanotek.entities.metamodel.query.criteria;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

public abstract class AbstractBrainzPredicate implements Predicate {

	private Predicate delegatePredicate;
	
	public AbstractBrainzPredicate() {
	}

	public AbstractBrainzPredicate(Predicate delegatePredicate) {
		super();
		this.delegatePredicate = delegatePredicate;
	}

	public Predicate isNull() {
		return delegatePredicate.isNull();
	}

	public Class<? extends Boolean> getJavaType() {
		return delegatePredicate.getJavaType();
	}

	public Selection<Boolean> alias(String name) {
		return delegatePredicate.alias(name);
	}

	public Predicate isNotNull() {
		return delegatePredicate.isNotNull();
	}

	public BooleanOperator getOperator() {
		return delegatePredicate.getOperator();
	}

	public String getAlias() {
		return delegatePredicate.getAlias();
	}

	public Predicate in(Object... values) {
		return delegatePredicate.in(values);
	}

	public boolean isCompoundSelection() {
		return delegatePredicate.isCompoundSelection();
	}

	public boolean isNegated() {
		return delegatePredicate.isNegated();
	}

	public List<Selection<?>> getCompoundSelectionItems() {
		return delegatePredicate.getCompoundSelectionItems();
	}

	public Predicate in(Expression<?>... values) {
		return delegatePredicate.in(values);
	}

	public List<Expression<Boolean>> getExpressions() {
		return delegatePredicate.getExpressions();
	}

	public Predicate in(Collection<?> values) {
		return delegatePredicate.in(values);
	}

	public Predicate not() {
		return delegatePredicate.not();
	}

	public Predicate in(Expression<Collection<?>> values) {
		return delegatePredicate.in(values);
	}

	public <X> Expression<X> as(Class<X> type) {
		return delegatePredicate.as(type);
	}

}
