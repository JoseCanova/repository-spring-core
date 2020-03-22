package org.nanotek.beans.entity;

import java.util.function.Consumer;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.entities.immutables.LongIdNameEntityBase;

@SuppressWarnings("serial")
@Entity
@Table(name="work_name")
public class WorkName implements LongIdNameEntityBase<String>{
	
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
