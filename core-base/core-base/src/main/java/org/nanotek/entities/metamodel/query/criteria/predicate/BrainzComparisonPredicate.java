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
import org.hibernate.query.criteria.internal.predicate.ComparisonPredicate;
import org.hibernate.query.criteria.internal.predicate.ComparisonPredicate.ComparisonOperator;
import org.nanotek.entities.metamodel.query.criteria.BrainzCriteriaBuilder;

public class BrainzComparisonPredicate<X extends ComparisonPredicate> extends AbstractBrainzPredicate<X> {

	private static final long serialVersionUID = 7561918863847687221L;

	public BrainzComparisonPredicate(BrainzCriteriaBuilder criteriaBuilder, X predicate) {
		super(criteriaBuilder, predicate);
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

	public ExpressionImplementor<BigInteger> asBigInteger() {
		return delegatePredicate.asBigInteger();
	}

	public ComparisonOperator getComparisonOperator() {
		return delegatePredicate.getComparisonOperator();
	}

	public ComparisonOperator getComparisonOperator(boolean isNegated) {
		return delegatePredicate.getComparisonOperator(isNegated);
	}

	public ExpressionImplementor<String> asString() {
		return delegatePredicate.asString();
	}

	public Expression getLeftHandOperand() {
		return delegatePredicate.getLeftHandOperand();
	}

	public Expression getRightHandOperand() {
		return delegatePredicate.getRightHandOperand();
	}

	public void registerParameters(ParameterRegistry registry) {
		delegatePredicate.registerParameters(registry);
	}

	public boolean equals(Object obj) {
		return delegatePredicate.equals(obj);
	}

	public String render(boolean isNegated, RenderingContext renderingContext) {
		return delegatePredicate.render(isNegated, renderingContext);
	}

	public String toString() {
		return delegatePredicate.toString();
	}

			
}
