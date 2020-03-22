package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.BarCodeEntity;

public interface MutableBarCodeEntity<K extends Serializable> extends BarCodeEntity<K> {

	void setBarCode(K k);
	
}
