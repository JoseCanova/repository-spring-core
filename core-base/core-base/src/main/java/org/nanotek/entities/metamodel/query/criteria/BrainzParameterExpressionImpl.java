package org.nanotek.entities.metamodel.query.criteria;

import javax.persistence.criteria.ParameterExpression;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.expression.ParameterExpressionImpl;

public class BrainzParameterExpressionImpl<X> extends ParameterExpressionImpl<X> {

	private static final long serialVersionUID = 8081029306725914206L;
	
	public BrainzParameterExpressionImpl (BrainzCriteriaBuilder brainzCriteriaBuilder , ParameterExpressionImpl<X> param){ 
		super(brainzCriteriaBuilder.getDelegateCriteriaBuilder() , param.getJavaType() , param.getPosition() );
	}

	public BrainzParameterExpressionImpl(CriteriaBuilderImpl criteriaBuilder, Class<X> javaType, Integer position) {
		super(criteriaBuilder, javaType, position);
	}

	public BrainzParameterExpressionImpl(CriteriaBuilderImpl criteriaBuilder, Class<X> javaType, String name) {
		super(criteriaBuilder, javaType, name);
	}

	public BrainzParameterExpressionImpl(CriteriaBuilderImpl criteriaBuilder, Class<X> javaType) {
		super(criteriaBuilder, javaType);
	}

	
	
}
