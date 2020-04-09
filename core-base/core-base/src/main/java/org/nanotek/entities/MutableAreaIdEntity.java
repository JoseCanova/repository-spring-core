package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.AreaIdEntity;

public interface MutableAreaIdEntity<K> extends AreaIdEntity<K>{

	void setAreaId(K k);
	
}
