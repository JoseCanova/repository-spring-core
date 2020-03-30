package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseAreaTypeEntity;

@Entity
@DiscriminatorValue(value = "AreaType")
public class AreaType<E extends AreaType<E>> 
extends BaseType<E> 
implements  BaseAreaTypeEntity<E>{

	private static final long serialVersionUID = 5334032717060542549L;
	
	public AreaType() {
		super();
	}

	public AreaType(Area<?> area) {
		super();
	}

	public AreaType(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
	}
	
	public AreaType(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
	}

	public AreaType(@NotNull Long typeId) {
		super(typeId);
	}

}
