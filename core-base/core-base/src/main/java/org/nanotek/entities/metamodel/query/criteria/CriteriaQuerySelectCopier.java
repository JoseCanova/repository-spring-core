package org.nanotek.entities.metamodel.query.criteria;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Selection;

import org.nanotek.BaseEntity;
import org.nanotek.IdBase;
import org.nanotek.entities.metamodel.BrainzMetaModelUtil;

public  class CriteriaQuerySelectCopier<X extends IdBase<X,Y>, Y extends BaseEntity<?,?>>  extends BrainzCriteriaQuery<X, Y> {

	private static final long serialVersionUID = 3094942858997785504L;

	
	public CriteriaQuerySelectCopier(BrainzCriteriaQuery brainzCriteriaQuery,Selection<Y> selection) {
		super(brainzCriteriaQuery);
		this.criteriaQuery = criteriaQuery.select(new AbstractBrainzSelection<Y>(selection) {
		});
	}

	public CriteriaQuerySelectCopier(Class<Y> id, EntityManagerFactory entityManagerFactory,
			BrainzMetaModelUtil brainzMetaModelUtil, CriteriaBuilder criteriaBuilder,
			CriteriaQuery<Y> criteriaQuery,Selection<Y> selection) {
		super(id, entityManagerFactory, brainzMetaModelUtil, criteriaBuilder, criteriaQuery);
		this.criteriaQuery = criteriaQuery.select(new AbstractBrainzSelection<Y>(selection) {
			
		});
	}
	
	@Override
	public CriteriaQuery<Y> select(Selection<? extends Y> selection) {
		return this;
	}

}
