package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.Base;
import org.nanotek.ImmutableBase;

@Entity
@Table(name="work")
public class Work<K extends Work<K>> extends  BrainzBaseEntity<K> {
	
	private String gid; 
	private String type; 
	private Long iswc; 
	
	
	
	public String getGid() {
		return gid;
	}


	public void setGid(String gid) {
		this.gid = gid;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Long getIswc() {
		return iswc;
	}


	public void setIswc(Long iswc) {
		this.iswc = iswc;
	}

}
