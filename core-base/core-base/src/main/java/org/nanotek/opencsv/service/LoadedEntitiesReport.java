package org.nanotek.opencsv.service;

public class LoadedEntitiesReport{
	
	private Class<?> entity;
	private Long countedEntities;
	
	public LoadedEntitiesReport(Class<?> entity, Long countedEntities) {
		this.entity=entity;
		this.countedEntities=countedEntities;
	}
	public Class<?> entity(){
		return this.entity;
	}
	public Long countedEntities(){
		return this.countedEntities;
	}
}