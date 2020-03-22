package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.DescriptionEntity;

public interface MutableDescriptionEntity<K extends Serializable> extends DescriptionEntity<K>{
	void setDescription(K k);
}
