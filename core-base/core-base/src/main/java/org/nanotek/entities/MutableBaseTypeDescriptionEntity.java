package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.BaseTypeDescriptionEntity;

public interface MutableBaseTypeDescriptionEntity<K> extends BaseTypeDescriptionEntity<K> {

	void setBaseTypeDescription(K k);
	
}
