package org.nanotek.service.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.nanotek.BrainzKeyQuerySupport;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.repository.jpa.BrainzBaseEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Primary
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BrainzPersistenceService 
<B extends BrainzBaseEntity<B>>
extends 
BasePersistenceService<B, BrainzBaseEntityRepository<B>>
implements BrainzKeyQuerySupport<B>
{

	private static final Logger log = LoggerFactory.getLogger(BrainzPersistenceService.class);
	
	@PersistenceContext
	EntityManager entityManager;
	
	public BrainzPersistenceService(@Autowired
			BrainzBaseEntityRepository<B> repository) {
		super(repository);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public BrainzBaseEntityRepository<B> getBaseRepository() {
		return baseRepository;
	}

}
