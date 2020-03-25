package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ValueEntity;

public interface MutableValueEntity<K extends Serializable> extends  ValueEntity<K>{

	void setValue(K k );
	
}
