package org.nanotek.beans.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableNameEntity;
import org.nanotek.entities.MutableSortNameEntity;

@Entity
@DiscriminatorValue(value = "LongIdSortNameEntity")
public class LongIdSortName<K extends LongIdSortName<K>> extends 
BrainzBaseEntity<K> implements MutableSortNameEntity<String> ,
MutableNameEntity<String>{

	private static final long serialVersionUID = -3442197714885490996L;

	@NotBlank
	@Column(name="sort_name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	protected String sortName;
	
	@NotNull
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String name;


	public LongIdSortName() {
	}

	public LongIdSortName(@NotBlank String name) {
		this.name = name;
	}
	
	public LongIdSortName(@NotBlank String name , @NotBlank String sortName) {
		this.name = name;
		this.sortName = sortName;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
