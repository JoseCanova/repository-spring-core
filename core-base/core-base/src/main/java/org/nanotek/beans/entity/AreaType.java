package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseAreaTypeEntity;
import org.nanotek.entities.MutableAreaEntity;

@Entity
@DiscriminatorValue(value = "AreaType")
public class AreaType<E extends AreaType<E>> 
extends BaseType<E> 
implements  BaseAreaTypeEntity<E>, MutableAreaEntity<Area<?>>{

	private static final long serialVersionUID = 5334032717060542549L;
	
	@OneToOne
	public Area<?> area;
	
	public AreaType() {
	}

	
	
	public AreaType(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
	}


	
	public AreaType(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
		// TODO Auto-generated constructor stub
	}



	public AreaType(@NotNull Long typeId) {
		super(typeId);
	}

	@Override
	public Area<?> getArea() {
		return area;
	}

	@Override
	public void setArea(Area<?> k) {
			this.area = k;
	}

}
