package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="work_name")
public class WorkName<K extends WorkName<K>> extends BrainzBaseEntity<K>{
	
	private Long id; 
	private String name; 
	
	
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
}
