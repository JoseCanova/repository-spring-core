package org.nanotek.entities.immutables;  

import java.io.Serializable;

import org.nanotek.Positionable;

public interface PositionEntity<K> extends Positionable<K> {
	@Override
	K getPosition();
}
