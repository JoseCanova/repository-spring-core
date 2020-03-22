package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.Base;

@Entity
@Table(name="url")
public class Url<K extends Url<K>> implements Base<K> {

	private Long id; 
	private String gid; 
	private String url; 
	private String description; 
	private String refCount; 
	
	public Url() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
