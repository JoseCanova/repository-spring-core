package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseMediumFormatEntity;
import org.nanotek.entities.MutableDescriptionEntity;
import org.nanotek.entities.MutableMediumFormatNameEntity;
import org.nanotek.entities.MutableYearEntity;

@Entity
@Table(name="medium_format",
uniqueConstraints = 
{@UniqueConstraint(name="uk_medium_format_id",columnNames = {"medium_format_id"})})
public class MediumFormat<K extends MediumFormat<K>> 
extends BrainzBaseEntity<K> implements 
BaseMediumFormatEntity<K>,
MutableDescriptionEntity<String>,
MutableMediumFormatNameEntity<String>,
MutableYearEntity<Integer>{

	private static final long serialVersionUID = 8104913204474210789L;
	
	@NotNull
	@Column(name="mediumFormatId" , insertable=true,nullable=false)
	public Long mediumFormatId;
	
	@NotBlank
	@Column(name = "name" , insertable=true , nullable=false , updatable=true , columnDefinition = "VARCHAR NOT NULL")
	public String mediumFormatName; 
	
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
		this.mediumFormatName = name;
		this.parent = parent;
		this.year = year;
		this.hasDiscId = hasDiscId;
		this.gid = gid;
		this.description = description;
	}


	public String getMediumFormatName() {
		return mediumFormatName;
	}

	public void setMediumFormatName(String name) {
		this.mediumFormatName = name;
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