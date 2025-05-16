package org.nanotek.entities.metamodel.query.criteria.predicate;

import java.util.Collection;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.ParameterRegistry;
import org.hibernate.query.criteria.internal.compile.RenderingContext;
import org.hibernate.query.criteria.internal.predicate.AbstractSimplePredicate;
import org.hibernate.query.criteria.internal.predicate.PredicateImplementor;
import org.nanotek.entities.metamodel.query.criteria.BrainzCriteriaBuilder;

public abstract class AbstractBrainzPredicate
<X extends PredicateImplementor> 
extends AbstractSimplePredicate  {

	private static final long serialVersionUID = -9145232851862512379L;

	protected X delegatePredicate;
	
	protected BrainzCriteriaBuilder criteriaBuilder;


	public AbstractBrainzPredicate(BrainzCriteriaBuilder criteriaBuilder, X predicate) {
		super(criteriaBuilder.getDelegateCriteriaBuilder());
		this.criteriaBuilder = criteriaBuilder;
		this.delegatePredicate = predicate;
	}

	public Predicate isNull() {
		return super.isNull();
	}

	public String render(RenderingContext renderingContext) {
		return delegatePredicate.render(renderingContext);
	}

	public CriteriaBuilderImpl criteriaBuilder() {
		return delegatePredicate.criteriaBuilder();
	}

	public boolean isJunction() {
		return delegatePredicate.isJunction();
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

	public String render(boolean isNegated, RenderingContext renderingContext) {
		return delegatePredicate.render(isNegated, renderingContext);
	}

	public Predicate in(Object... values) {
		return delegatePredicate.in(values);
	}

	public boolean isNegated() {
		return delegatePredicate.isNegated();
	}

	public Predicate in(Expression<?>... values) {
		return delegatePredicate.in(values);
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
