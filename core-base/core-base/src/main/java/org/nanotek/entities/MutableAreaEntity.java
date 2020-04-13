package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.AreaEntity;

public interface MutableAreaEntity<K> extends AreaEntity<K>{

	void setArea(K k);
	
}
