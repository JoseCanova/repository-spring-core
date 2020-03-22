package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.SortNameEntity;

public interface MutableSortNameEntity<K extends Serializable> extends SortNameEntity<K>{
  
	void setSortName(K k);
	
}
