package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="release_name")
public class ReleaseName<K extends ReleaseName<K>> extends BrainzBaseEntity<K> {

	private String name; 
	private String refCount;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRefCount() {
		return refCount;
	}
	
	public void setRefCount(String refCount) {
		this.refCount = refCount;
	}

}
