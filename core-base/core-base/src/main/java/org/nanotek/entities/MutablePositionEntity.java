package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.PositionEntity;

public interface MutablePositionEntity<K> extends PositionEntity<K>{
	void setPosition(K k);
}
