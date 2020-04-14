package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseReleaseGroupPrimaryTypeEntity;

@Entity
@DiscriminatorValue(value = "ReleaseGroupPrimaryType")
public class ReleaseGroupPrimaryType
<K extends ReleaseGroupPrimaryType<K>> 
extends BaseType<K>
implements BaseReleaseGroupPrimaryTypeEntity<K>{

	private static final long serialVersionUID = 3855412565715717271L;

	public ReleaseGroupPrimaryType() {
		super();
	}

	public ReleaseGroupPrimaryType(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
	}

	public ReleaseGroupPrimaryType(@NotNull Long typeId) {
		super(typeId);
	}

	public ReleaseGroupPrimaryType(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
	}
	
}
