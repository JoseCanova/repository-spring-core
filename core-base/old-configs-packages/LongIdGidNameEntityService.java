package org.nanotek.service;

import org.nanotek.beans.entity.LongIdGidName;
import org.nanotek.repository.jpa.LongIdGidNameEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class LongIdGidNameEntityService<O extends LongIdGidName<O> , 
B extends LongIdGidNameEntityRepository<O>> {

	@Autowired
	LongIdGidNameEntityRepository<O> rep;
	
	public LongIdGidNameEntityService(B rep) {
	}

}
