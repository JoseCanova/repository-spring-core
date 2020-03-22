package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.AreaEndDateEntity;

public interface MutableAreaEndDateEntity<K extends Serializable> extends AreaEndDateEntity<K> {

	void setAreaEndDate(K k);
	
}
