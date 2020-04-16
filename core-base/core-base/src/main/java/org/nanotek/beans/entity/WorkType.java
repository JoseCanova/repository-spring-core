package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseWorkTypeEntity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "WorkType")
public class WorkType
<K extends WorkType<K>> 
extends BaseType<K> 
implements BaseWorkTypeEntity<K>
{

	public WorkType() {
		super();
	}

	public WorkType(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
	}

	public WorkType(@NotNull Long typeId) {
		super(typeId);
	}

	public WorkType(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
	}

}
