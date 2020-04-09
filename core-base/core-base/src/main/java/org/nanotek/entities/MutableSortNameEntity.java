package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.SortNameEntity;

public interface MutableSortNameEntity<K> extends SortNameEntity<K>{
  
	void setSortName(K k);
	
}
