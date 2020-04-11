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
import org.hibernate.query.criteria.internal.predicate.LikePredicate;
import org.nanotek.entities.metamodel.query.criteria.BrainzCriteriaBuilder;

public class BrainzLikePredicate<X extends LikePredicate> extends AbstractBrainzPredicate<X> {

	private static final long serialVersionUID = -6598102316285556799L;
	
	
	public BrainzLikePredicate(BrainzCriteriaBuilder criteriaBuilder , X delegatePredicate) { 
		super(criteriaBuilder,delegatePredicate);
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

	public ExpressionImplementor<Float> asFloat() {
		return delegatePredicate.asFloat();
	}

	public ExpressionImplementor<Double> asDouble() {
		return delegatePredicate.asDouble();
	}

	public int hashCode() {
		return delegatePredicate.hashCode();
	}

	public ExpressionImplementor<BigDecimal> asBigDecimal() {
		return delegatePredicate.asBigDecimal();
	}

	public Expression<Character> getEscapeCharacter() {
		return delegatePredicate.getEscapeCharacter();
	}

	public Expression<String> getMatchExpression() {
		return delegatePredicate.getMatchExpression();
	}

	public ExpressionImplementor<BigInteger> asBigInteger() {
		return delegatePredicate.asBigInteger();
	}

	public Expression<String> getPattern() {
		return delegatePredicate.getPattern();
	}

	public void registerParameters(ParameterRegistry registry) {
		delegatePredicate.registerParameters(registry);
	}

	public ExpressionImplementor<String> asString() {
		return delegatePredicate.asString();
	}

	public String render(boolean isNegated, RenderingContext renderingContext) {
		return delegatePredicate.render(isNegated, renderingContext);
	}

	public boolean equals(Object obj) {
		return delegatePredicate.equals(obj);
	}

	public String toString() {
		return delegatePredicate.toString();
	}
	
	
}
