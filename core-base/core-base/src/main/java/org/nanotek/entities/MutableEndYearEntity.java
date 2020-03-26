package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.EndYearEntity;

public interface MutableEndYearEntity<T extends Serializable> extends EndYearEntity<T>{
	
	void setEndYear(T t);

}
