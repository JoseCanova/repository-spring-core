package org.nanotek.opencsv.service;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotNull;

import org.nanotek.LoadedEntityBean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoadedEntitiesReport implements 
LoadedEntityBean<LoadedEntitiesReport>{
	
	private static final long serialVersionUID = -5090714209568802878L;
	
	private Class<?> entity;
	private Long countedEntities;
	
	@JsonCreator
	public LoadedEntitiesReport(@JsonbProperty(value = "entity")
			@NotNull Class<?> entity,
			@JsonbProperty(value = "value")
			@NotNull  Long countedEntities) {
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