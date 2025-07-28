package org.nanotek.opencsv.service;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotNull;

import org.nanotek.LoadedEntityBean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoadedEntitiesReport implements 
LoadedEntityBean<LoadedEntitiesReport>{
	
	private static final long serialVersionUID = -5090714209568802878L;
	
	@JsonProperty(value = "entity")
	private Class<?> entity;
	
	@JsonProperty(value = "value")
	private Long countedEntities;
	
	@JsonCreator
	public LoadedEntitiesReport(@JsonProperty(value = "entity")
			@NotNull Class<?> entity,
			@JsonProperty(value = "value")
			@NotNull  Long countedEntities) {
		this.entity=entity;
		this.countedEntities=countedEntities;
	}
	
	@JsonProperty(value = "entity")
	public Class<?> entity(){
		return this.entity;
	}
	
	@JsonProperty(value = "value")
	public Long value(){
		return this.countedEntities;
	}
	
	@Override
	public String toString() {
		return "LoadedEntitiesReport [entity=" + entity + ", countedEntities=" + countedEntities + "]";
	}
}