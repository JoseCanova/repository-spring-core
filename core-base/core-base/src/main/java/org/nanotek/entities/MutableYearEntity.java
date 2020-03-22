package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.YearEntity;

public interface MutableYearEntity<K extends Serializable> extends YearEntity<K>{ 

	void setYear(K k);
	
}
