package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.entities.immutables.LongIdEntityBase;

@SuppressWarnings("serial")
@Entity
@Table(name="label_name")
public class LabelName implements LongIdEntityBase {

	private Long id; 
	
	private String name; 
	
	@Override
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
