package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.EndYearEntity;

public interface MutableEndYearEntity<T> extends EndYearEntity<T>{
	
	void setEndYear(T t);

}
