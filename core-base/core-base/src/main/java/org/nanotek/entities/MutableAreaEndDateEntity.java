package org.nanotek.entities;

import org.nanotek.entities.immutables.AreaEndDateEntity;

public interface MutableAreaEndDateEntity<K> extends AreaEndDateEntity<K> {

	void setAreaEndDate(K k);
	
}
