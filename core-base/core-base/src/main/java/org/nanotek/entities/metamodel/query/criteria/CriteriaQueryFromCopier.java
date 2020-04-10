package org.nanotek.entities.metamodel.query.criteria;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.nanotek.BaseEntity;
import org.nanotek.IdBase;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;

public class CriteriaQueryFromCopier <X extends IdBase<X,Y>, Y extends BaseEntity<?,?>>  extends BrainzCriteriaQuery<X, Y> {

	private static final long serialVersionUID = 3094942858997785504L;

	public CriteriaQueryFromCopier(Class<Y> id, EntityManagerFactory entityManagerFactory,
			BrainzMetaModelUtil brainzMetaModelUtil, CriteriaBuilder criteriaBuilder,
			CriteriaQuery<Y> criteriaQuery) {
		super(id, entityManagerFactory, brainzMetaModelUtil, criteriaBuilder, criteriaQuery);
	}
 
	
}
