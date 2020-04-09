package org.nanotek.entities.metamodel.query;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;
import org.nanotek.Base;
import org.nanotek.BaseEntity;
import org.nanotek.IdBase;

public class BrainzCriteriaQuery
<X extends IdBase<X,Y>, Y extends BaseEntity<?,?>> 
extends CriteriaQueryImpl<Y>
implements IdBase<X,Class<Y>>{
	
	private static final long serialVersionUID = 7036293271761390183L;

	private Class<Y> id;
	
	private CriteriaBuilderImpl criteriaBuilder;

	public BrainzCriteriaQuery(CriteriaBuilderImpl criteriaBuilder, Class<Y> returnType) {
		super(criteriaBuilder, returnType);
		this.id = returnType;
		this.criteriaBuilder = criteriaBuilder;
		afterPropertiesSet();
	}

	private void afterPropertiesSet() {
	}

	@Override
	public Class<Y> getId() {
		return id;
	}


}
