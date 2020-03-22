package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.nanotek.entities.BaseMediumFormatEntity;
import org.nanotek.entities.MutableMediumFomatEntity;

@Entity
@Table(name="medium_format")
public class MediumFormat<K extends MediumFormat<K>> 
extends BrainzBaseEntity<K> implements 
																  BaseMediumFormatEntity<K>,
																  MutableMediumFomatEntity<K>{

	private static final long serialVersionUID = 8104913204474210789L;
	
	@NotBlank
	@Size(min=1,max=100)
	@Column(name = "name" , length=100 , insertable=true , nullable=false , updatable=true)
	public String name; 
	
	@Column(name = "parent")
	private Long parent;
	
	@Column(name = "year")
	private Integer year; 
	
	@Column(name = "hasDiscId", length=6, nullable=false)
	private String hasDiscId;
	
	@NotBlank
	@Column(name = "gid" , length=50 , insertable=true , nullable=false , updatable=true)
	private String gid;
	
	@Column(name="description" , length=4000)
	private String description;
	
	public MediumFormat() { 
		super();
	}
	
	public MediumFormat(
			@NotBlank @Size(min = 1, max = 100) String name, 
			Long parent, Integer year, String hasDiscId,
			@NotBlank String gid, String description) {
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

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}