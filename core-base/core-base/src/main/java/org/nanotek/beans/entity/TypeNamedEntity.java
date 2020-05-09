package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableTypeNameEntity;

@MappedSuperclass
public abstract class TypeNamedEntity
<E extends TypeNamedEntity<E>> extends BrainzBaseEntity<E>
implements 
MutableGidEntity<UUID>,MutableTypeNameEntity<String>{

	private static final long serialVersionUID = -5235727515178240574L;

	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	public UUID gid;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String typeName;

	
	public TypeNamedEntity() {
	}

	public TypeNamedEntity(@NotNull String name) {
		this.typeName = name;
	}

	public TypeNamedEntity(@NotNull UUID gid, @NotBlank String name) {
		this.gid = gid; 
		this.typeName = name;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String name) {
		this.typeName = name;
	}

}
