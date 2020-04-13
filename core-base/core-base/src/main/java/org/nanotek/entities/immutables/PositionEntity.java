package org.nanotek.entities.immutables;  

import org.nanotek.Positionable;

public interface PositionEntity<K> extends Positionable<K> {
	K getPosition();
}
