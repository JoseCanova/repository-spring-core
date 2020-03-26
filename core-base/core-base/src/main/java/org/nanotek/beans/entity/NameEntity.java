package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableNameEntity;

public class NameEntity<K extends NameEntity<K>> extends BrainzBaseEntity<K> 
implements MutableNameEntity<String>{

	
	@NotNull
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String name;


	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String k) {
		this.name = k;
	}

}
