package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.entities.BaseReleaseGroupPrimaryTypeEntity;

@Entity
@Table(name="release_group_primary_type")
public class ReleaseGroupPrimaryType<K extends ReleaseGroupPrimaryType<K>> 
extends LongIdGidName<K> 
implements BaseReleaseGroupPrimaryTypeEntity{

	private static final long serialVersionUID = 3855412565715717271L;
	
	@Column(name="name" , nullable = false , length=255)
	private String name; 
	@Column(name="gid" , nullable = false , length=50)
	private String gid;
	
	public ReleaseGroupPrimaryType() {
	}
	
	public ReleaseGroupPrimaryType(String name, String gid) {
		super();
		this.name = name;
		this.gid = gid;
	}
}
