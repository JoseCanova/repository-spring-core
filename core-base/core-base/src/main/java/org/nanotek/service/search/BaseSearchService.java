package org.nanotek.service.search;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.search.engine.ProjectionConstants;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.nanotek.BaseException;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier(value = "BaseSearchService")
public class BaseSearchService<B extends BrainzBaseEntity<B>>{


	@PersistenceUnit
	EntityManagerFactory entityManagerFactory;

	
    public BaseSearchService() {
        super();
    }


    public FullTextEntityManager createIndexer(EntityManager entityManager) {

        try {
             return Search.getFullTextEntityManager(entityManager);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(e);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED , propagation = Propagation.REQUIRES_NEW)
    public <S extends B> List<B> findByEntityName(Class<B> entityClass,String searchQuery) { 
    	FullTextEntityManager indexer = createIndexer(entityManagerFactory.createEntityManager());
    	QueryBuilder qb = indexer.getSearchFactory()
    									.buildQueryBuilder()
    									.forEntity(entityClass)
    									.get();
    	org.apache.lucene.search.Query luceneQuery = qb
											    		.simpleQueryString()
											    		.onField("name")
											    		.matching(searchQuery)
											    		.createQuery();
    	return indexer
			    	.createFullTextQuery( luceneQuery, entityClass )
			    	.setProjection( ProjectionConstants.THIS, ProjectionConstants.SCORE )
			    	.getResultList();
    }
    
}