package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.TypeEntity;

public interface MutableTypeEntity<K> extends TypeEntity<K>{

	void setType(K k);
	
}
