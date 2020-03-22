package org.nanotek.service;

import org.nanotek.beans.entity.SequenceLongBase;
import org.nanotek.repository.jpa.SequenceLongBaseRepository;
import org.nanotek.service.jpa.BasePersistenceService;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class IdBasePersistenceService <O extends SequenceLongBase<O,Long>, R extends SequenceLongBaseRepository<R,O>> 
extends BasePersistenceService<O,R>{
	
	public IdBasePersistenceService(@Autowired R baseRepository) {
		super(baseRepository);
	}
	
	public Iterable<O> findByNameContainingIgnoreCase(String name){
		return null;
	}

}
