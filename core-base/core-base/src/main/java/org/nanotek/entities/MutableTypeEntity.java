package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.TypeEntity;

public interface MutableTypeEntity<K extends Serializable> extends TypeEntity<K>{

	void setType(K k);
	
}
