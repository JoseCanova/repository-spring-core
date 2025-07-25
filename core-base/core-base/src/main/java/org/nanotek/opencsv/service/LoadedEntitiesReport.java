package org.nanotek.opencsv.service;

import org.nanotek.LoadedEntityBean;

public class LoadedEntitiesReport implements 
EntityAccessor<Class<?>> ,EntityValueAccessor<Long> , LoadedEntityBean<LoadedEntitiesReport>{
	
	private static final long serialVersionUID = -5090714209568802878L;
	
	private Class<?> entity;
	private Long countedEntities;
	
	public LoadedEntitiesReport(Class<?> entity, Long countedEntities) {
		this.entity=entity;
		this.countedEntities=countedEntities;
	}
	public Class<?> entity(){
		return this.entity;
	}
	public Long value(){
		return this.countedEntities;
	}
}