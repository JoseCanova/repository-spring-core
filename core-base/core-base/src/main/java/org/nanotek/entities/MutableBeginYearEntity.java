package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.BeginYearEntity;

public interface MutableBeginYearEntity<K extends Serializable> extends BeginYearEntity<K>{ 

	void setBeginYear(K k) ;
	
}
