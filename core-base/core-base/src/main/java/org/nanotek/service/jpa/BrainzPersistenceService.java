package org.nanotek.service.jpa;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.nanotek.BaseEntity;
import org.nanotek.BrainzKeyQuerySupport;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.repository.jpa.BrainzBaseEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BrainzPersistenceService 
<B extends BrainzBaseEntity<B>, C extends BrainzBaseEntityRepository<B>>
extends 
BasePersistenceService<B, C>
implements BrainzKeyQuerySupport<B>
{

	private static final Logger log = LoggerFactory.getLogger(BrainzPersistenceService.class);
	
	@PersistenceContext
	EntityManager entityManager;
	
	public BrainzPersistenceService(@Autowired
			BrainzBaseEntityRepository<B> repository) {
		super((C) repository);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Async
	public void verifyAndStore(BaseEntity<?, ?> id) {
		B theB = (B)id;
		Class<B> clazz = castClass(id);
		Optional<Stream<?>> theStream = prepareDinamicQuery(clazz, id);
		if(!theStream.isPresent()) {
			save(theB);
		}
	}

	@SuppressWarnings("unchecked")
	private Class<B> castClass(BaseEntity<?, ?> id) {
		return ((Class<B>) id.getClass().asSubclass(BrainzBaseEntity.class));
	}

}
