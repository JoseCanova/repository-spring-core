package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ChildOrderEntity;

public interface MutableChildOrderEntity<K extends Serializable> extends ChildOrderEntity<K>{
	void setChildOrder(K k);
}
