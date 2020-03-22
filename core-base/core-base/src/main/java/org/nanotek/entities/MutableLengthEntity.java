package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.LengthtEntity;

public interface MutableLengthEntity<K extends Serializable> extends LengthtEntity<K>{

	void setLength(K k);
	
	
}
