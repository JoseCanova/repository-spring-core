package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.DescriptionBaseEntity;

public interface MutableDescriptionBaseEntity<K> extends DescriptionBaseEntity<K>{

	void setDescription(K k);
	
}
