package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseMediumFormatEntity;
import org.nanotek.entities.MutableDescriptionEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableNameEntity;
import org.nanotek.entities.MutableParentEntity;
import org.nanotek.entities.MutableYearEntity;

@Entity
@Table(name="medium_format")
public class MediumFormat<K extends MediumFormat<K>> 
extends BrainzBaseEntity<K> implements 
																  BaseMediumFormatEntity<K>,
																  MutableNameEntity<String>,
																  MutableParentEntity<Long>,
																  MutableYearEntity<Integer>,
																  MutableGidEntity<UUID>,
																  MutableDescriptionEntity<String>{

	private static final long serialVersionUID = 8104913204474210789L;
	
	@NotNull
	@Column(name="mediumFormatId" , insertable=true,nullable=false)
	public Long mediumFormatId;
	
	@NotBlank
	@Column(name = "name" , insertable=true , nullable=false , updatable=true , columnDefinition = "VARCHAR NOT NULL")
	public String name; 
	
	@Column(name = "parent")
	private Long parent;
	
	@Column(name = "year")
	private Integer year; 
	
	@Column(name = "hasDiscId", length=6, nullable=false)
	private String hasDiscId;
	
	@NotNull
	@Column(name = "gid" , length=50 , insertable=true , nullable=false , updatable=true)
	private UUID gid;
	
	@Column(name="description" , length=4000)
	private String description;
	
	public MediumFormat() { 
		super();
	}
	
	public MediumFormat(
			@NotBlank  String name, 
			Long parent, Integer year, String hasDiscId,
			@NotBlank UUID gid, String description) {
		this.name = name;
		this.parent = parent;
		this.year = year;
		this.hasDiscId = hasDiscId;
		this.gid = gid;
		this.description = description;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getHasDiscId() {
		return hasDiscId;
	}

	public void setHasDiscId(String hasDiscId) {
		this.hasDiscId = hasDiscId;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@BrainzKey(entityClass = MediumFormat.class, pathName = "mediumFormatId")
	public Long getMediumFormatId() {
		return mediumFormatId;
	}

	public void setMediumFormatId(Long mediumFormatId) {
		this.mediumFormatId = mediumFormatId;
	}

}