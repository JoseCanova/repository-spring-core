package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.BaseEntity;
import org.nanotek.entities.BaseMutableGidEntity;
import org.nanotek.entities.MutableGidEntity;

@MappedSuperclass
public abstract class LongIdGidName<K extends LongIdName<K>> 
extends LongIdName<K> 
implements  
BaseMutableGidEntity<K>
, MutableGidEntity<String>{

	
	
	private static final long serialVersionUID = 6451845781007858778L;
	
	@NotNull
	@Column(name="gid", nullable=false , columnDefinition = "VARCHAR(50) NOT NULL")
	protected String gid;

	
	public LongIdGidName() {
		super();
	}

	public LongIdGidName(@NotNull String name) {
		super(name);
	}
	

	public LongIdGidName(
								@NotNull  String gid, 
								@NotBlank String name) {
		super(name);
		this.gid = gid;
	}


	public void setGid(String gid) {
		this.gid = gid;
	}

	@Override
	public String getGid() {
		return gid;
	}
	
}
