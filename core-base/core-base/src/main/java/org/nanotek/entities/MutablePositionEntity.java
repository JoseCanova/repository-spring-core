package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.PositionEntity;

public interface MutablePositionEntity<K extends Serializable> extends PositionEntity<K>{
	void setPosition(K k);
}
