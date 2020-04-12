package org.nanotek.entities;

import org.nanotek.entities.immutables.YearEntity;

public interface MutableYearEntity<K> extends YearEntity<K>{ 

	void setYear(K k);
	
}
