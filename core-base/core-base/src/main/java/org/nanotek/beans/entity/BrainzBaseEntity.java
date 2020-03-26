package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.ManagedEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableNameEntity;

@MappedSuperclass
public class BrainzBaseEntity<K extends BrainzBaseEntity<K>> 
extends SequenceLongBase<K, Long> 
implements ManagedEntity<K>,
MutableNameEntity<String>,
MutableGidEntity<String>{

	private static final long serialVersionUID = -8896061351986644230L;

	public BrainzBaseEntity() {
	}

	@NotNull
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String name;

	@NotNull
	@Column(name="gid", nullable=false , columnDefinition = "VARCHAR(50) NOT NULL")
	protected String gid;


	public BrainzBaseEntity(@NotNull String name) {
		super();
		this.name = name;
	}

	public BrainzBaseEntity(
			@NotNull  String gid, 
			@NotBlank String name) {
		this.name = name;
		this.gid = gid;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String k) {
		this.name = k;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	@Override
	public String getGid() {
		return gid;
	}


}
