package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.Base;

@SuppressWarnings("serial")
@Entity
@Table(name="work_type")
public class WorkType implements Base{

	private String id; 
	
	private String name;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
