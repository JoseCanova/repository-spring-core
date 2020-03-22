package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.AreaBeginDateEntity;

public interface MutableAreaBeginDateEntity<K extends Serializable> extends AreaBeginDateEntity<K>{

	void setAreaBeginDate(K k);
	
}
