package org.nanotek.beans.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PositionEntityBase<K extends PositionEntityBase<K>> extends 
BrainzBaseEntity<K>{

	private static final long serialVersionUID = 3659277405774297177L;

	public PositionEntityBase() {
	}
	
}
