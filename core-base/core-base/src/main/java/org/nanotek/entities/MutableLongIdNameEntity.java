package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.LongIdNameEntity;

public interface MutableLongIdNameEntity<K> extends LongIdNameEntity<K>{
	
	void setName(K k);

}
