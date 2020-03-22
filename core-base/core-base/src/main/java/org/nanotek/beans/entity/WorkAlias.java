package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.Base;

@Entity
@Table(name="work_alias")
public class WorkAlias implements Base {

	private Long id; 
	
	private Long work; 
	
	private Long name; 
	
	private String locale; 
	
	
	public Long getId() {
		return id;
	}


	public Long getWork() {
		return work;
	}


	public void setWork(Long work) {
		this.work = work;
	}


	public Long getName() {
		return name;
	}


	public void setName(Long name) {
		this.name = name;
	}


	public String getLocale() {
		return locale;
	}


	public void setLocale(String locale) {
		this.locale = locale;
	}


	public void setId(Long id) {
		this.id = id;
	}

}
