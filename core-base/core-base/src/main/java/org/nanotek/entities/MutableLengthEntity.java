package org.nanotek.entities;

import org.nanotek.entities.immutables.LengthEntity;

public interface MutableLengthEntity<K> extends LengthEntity<K>{

	void setLength(K k);
	
	
}
