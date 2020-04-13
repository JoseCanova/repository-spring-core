package org.nanotek.entities.metamodel.query.criteria;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.ExpressionImplementor;
import org.hibernate.query.criteria.internal.ParameterRegistry;
import org.hibernate.query.criteria.internal.ValueHandlerFactory.ValueHandler;
import org.hibernate.query.criteria.internal.compile.RenderingContext;
import org.hibernate.query.criteria.internal.predicate.NegatedPredicateWrapper;
import org.nanotek.entities.metamodel.query.criteria.predicate.AbstractBrainzPredicate;

public class BrainzNegatedPredicateWrapper<X extends NegatedPredicateWrapper> 
extends AbstractBrainzPredicate<X>{

	private static final long serialVersionUID = 2640324969197852409L;
	
	public BrainzNegatedPredicateWrapper(BrainzCriteriaBuilder criteriaBuilder, X predicate) {
		super(criteriaBuilder, predicate);
	}

	public CriteriaBuilderImpl criteriaBuilder() {
		return delegatePredicate.criteriaBuilder();
	}

	public <X> Expression<X> as(Class<X> type) {
		return delegatePredicate.as(type);
	}

	public Selection<Boolean> alias(String alias) {
		return delegatePredicate.alias(alias);
	}

	public Class<Boolean> getJavaType() {
		return delegatePredicate.getJavaType();
	}

	public List<ValueHandler> getValueHandlers() {
		return delegatePredicate.getValueHandlers();
	}

	public Predicate isNull() {
		return delegatePredicate.isNull();
	}

	public Predicate isNotNull() {
		return delegatePredicate.isNotNull();
	}

	public Predicate in(Object... values) {
		return delegatePredicate.in(values);
	}

	public Predicate in(Expression<?>... values) {
		return delegatePredicate.in(values);
	}

	public ValueHandler<Boolean> getValueHandler() {
		return delegatePredicate.getValueHandler();
	}

	public Predicate in(Collection<?> values) {
		return delegatePredicate.in(values);
	}

	public String getAlias() {
		return delegatePredicate.getAlias();
	}

	public Predicate in(Expression<Collection<?>> values) {
		return delegatePredicate.in(values);
	}

	public ExpressionImplementor<Long> asLong() {
		return delegatePredicate.asLong();
	}

	public ExpressionImplementor<Integer> asInteger() {
		return delegatePredicate.asInteger();
	}

	public BooleanOperator getOperator() {
		return delegatePredicate.getOperator();
	}

	public ExpressionImplementor<Float> asFloat() {
		return delegatePredicate.asFloat();
	}

	public boolean isJunction() {
		return delegatePredicate.isJunction();
	}

	public boolean isNegated() {
		return delegatePredicate.isNegated();
	}

	public ExpressionImplementor<Double> asDouble() {
		return delegatePredicate.asDouble();
	}

	public Predicate not() {
		return delegatePredicate.not();
	}

	public int hashCode() {
		return delegatePredicate.hashCode();
	}

	public ExpressionImplementor<BigDecimal> asBigDecimal() {
		return delegatePredicate.asBigDecimal();
	}

	public void registerParameters(ParameterRegistry registry) {
		delegatePredicate.registerParameters(registry);
	}

	public ExpressionImplementor<BigInteger> asBigInteger() {
		return delegatePredicate.asBigInteger();
	}

	public String render(boolean isNegated, RenderingContext renderingContext) {
		return delegatePredicate.render(isNegated, renderingContext);
	}

	public ExpressionImplementor<String> asString() {
		return delegatePredicate.asString();
	}

	public String render(RenderingContext renderingContext) {
		return delegatePredicate.render(renderingContext);
	}

	public boolean equals(Object obj) {
		return delegatePredicate.equals(obj);
	}

	public String toString() {
		return delegatePredicate.toString();
	}

	
	
}
