package org.nanotek.entities.immutables;

import org.nanotek.Positionable;

public interface MediumPositionEntity<K> extends Positionable<K>{
K getMediumPosition();
}
