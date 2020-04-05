package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="url")
public class Url<K extends Url<K>> extends BrainzBaseEntity<K> {

	private String gid; 
	private String url; 
	private String description; 
	private String refCount; 
	
	public Url() {
	}


	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRefCount() {
		return refCount;
	}

	public void setRefCount(String refCount) {
		this.refCount = refCount;
	}

}
