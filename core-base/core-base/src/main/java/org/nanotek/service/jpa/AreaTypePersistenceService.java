package org.nanotek.service.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.nanotek.BrainzKeyQuerySupport;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.repository.jpa.SequenceLongBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaTypePersistenceService 
<B extends AreaType<B>, C extends SequenceLongBaseRepository<B>>
extends 
BasePersistenceService<B, C>
implements 
BrainzKeyQuerySupport<AreaType<?>>{

	@PersistenceContext
	EntityManager entityManager;
	
	public AreaTypePersistenceService(@Autowired
			SequenceLongBaseRepository<B> repository) {
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
