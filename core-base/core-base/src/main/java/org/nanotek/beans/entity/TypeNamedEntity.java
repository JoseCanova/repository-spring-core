package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableNameEntity;

@MappedSuperclass
public abstract class TypeNamedEntity
<E extends TypeNamedEntity<E>> extends BrainzBaseEntity<E> implements 
MutableGidEntity<UUID>,MutableNameEntity<String>{

	private static final long serialVersionUID = -5235727515178240574L;

	@NotNull
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	protected UUID gid;
	
	@NotNull
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String name;

	
	public TypeNamedEntity() {
	}

	public TypeNamedEntity(@NotNull String name) {
		this.name = name;
	}

	public TypeNamedEntity(@NotNull UUID gid, @NotBlank String name) {
		this.gid = gid; 
		this.name = name;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
