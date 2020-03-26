package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="work_alias")
public class WorkAlias<K extends WorkAlias<K>> extends BrainzBaseEntity<K> {

	private Long work; 
	

	
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
