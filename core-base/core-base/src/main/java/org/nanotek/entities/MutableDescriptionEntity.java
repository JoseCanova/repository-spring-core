package org.nanotek.entities;

import org.nanotek.entities.immutables.DescriptionEntity;

public interface MutableDescriptionEntity<K> extends DescriptionEntity<K>{
	void setDescription(K k);
}
