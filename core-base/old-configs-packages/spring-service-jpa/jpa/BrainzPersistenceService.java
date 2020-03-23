package org.nanotek.service.jpa;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.repository.jpa.BrainzBaseRepository;

public abstract class BrainzPersistenceService
<B extends BrainzBaseEntity<B> , C extends BrainzBaseRepository<B>> 
extends BasePersistenceService<B,C> {

	public BrainzPersistenceService() {}
	
	public BrainzPersistenceService(C b) {
		super(b);
	}
	
}
