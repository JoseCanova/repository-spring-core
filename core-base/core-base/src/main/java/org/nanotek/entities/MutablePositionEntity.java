package org.nanotek.entities;

import org.nanotek.entities.immutables.PositionEntity;

public interface MutablePositionEntity<K> extends PositionEntity<K>{
	void setPosition(K k);
}
