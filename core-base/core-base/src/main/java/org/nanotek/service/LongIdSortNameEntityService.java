package org.nanotek.service;

import org.nanotek.beans.entity.LongIdSortName;
import org.nanotek.repository.jpa.LongIdSortNameEntityRepository;


//TODO: implements service
public abstract class LongIdSortNameEntityService<O extends LongIdSortName<O> , R extends LongIdSortNameEntityRepository<O>> 
{   //extends LongIdNameEntityService<O, R> {

	public LongIdSortNameEntityService(R rep) {
	}
	
}
