package org.nanotek.entities;

import org.nanotek.entities.immutables.AreaBeginDateEntity;

public interface MutableAreaBeginDateEntity<K> extends AreaBeginDateEntity<K>{

	void setAreaBeginDate(K k);
	
}
