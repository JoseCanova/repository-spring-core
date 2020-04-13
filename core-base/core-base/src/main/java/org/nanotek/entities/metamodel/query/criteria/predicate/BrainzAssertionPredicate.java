package org.nanotek.entities.metamodel.query.criteria.predicate;

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
import org.hibernate.query.criteria.internal.predicate.BooleanAssertionPredicate;
import org.nanotek.entities.metamodel.query.criteria.BrainzCriteriaBuilder;

public class BrainzAssertionPredicate<X extends BooleanAssertionPredicate> extends AbstractBrainzPredicate<X> {
	
	private static final long serialVersionUID = 2563731341257632633L;

	public BrainzAssertionPredicate(BrainzCriteriaBuilder brainzCriteriaBuilder , X delegatePredicate ){ 
		super (brainzCriteriaBuilder,delegatePredicate);
	}

	public CriteriaBuilderImpl criteriaBuilder() {
		return delegatePredicate.criteriaBuilder();
	}

	public boolean isJunction() {
		return delegatePredicate.isJunction();
	}

	public BooleanOperator getOperator() {
		return delegatePredicate.getOperator();
	}

	public boolean isNegated() {
		return delegatePredicate.isNegated();
	}

	public Predicate not() {
		return delegatePredicate.not();
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

	public String render(RenderingContext renderingContext) {
		return delegatePredicate.render(renderingContext);
	}

	public List<ValueHandler> getValueHandlers() {
		return delegatePredicate.getValueHandlers();
	}

	public Expression<Boolean> getExpression() {
		return delegatePredicate.getExpression();
	}

	public Predicate isNull() {
		return delegatePredicate.isNull();
	}

	public Boolean getAssertedValue() {
		return delegatePredicate.getAssertedValue();
	}

	public Predicate isNotNull() {
		return delegatePredicate.isNotNull();
	}

	public void registerParameters(ParameterRegistry registry) {
		delegatePredicate.registerParameters(registry);
	}

	public Predicate in(Object... values) {
		return delegatePredicate.in(values);
	}

	public String render(boolean isNegated, RenderingContext renderingContext) {
		return delegatePredicate.render(isNegated, renderingContext);
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

	public ExpressionImplementor<Float> asFloat() {
		return delegatePredicate.asFloat();
	}

	public ExpressionImplementor<Double> asDouble() {
		return delegatePredicate.asDouble();
	}

	public ExpressionImplementor<BigDecimal> asBigDecimal() {
		return delegatePredicate.asBigDecimal();
	}

	public ExpressionImplementor<BigInteger> asBigInteger() {
		return delegatePredicate.asBigInteger();
	}

	public ExpressionImplementor<String> asString() {
		return delegatePredicate.asString();
	}

	public boolean equals(Object obj) {
		return delegatePredicate.equals(obj);
	}

	public String toString() {
		return delegatePredicate.toString();
	}
	
}
