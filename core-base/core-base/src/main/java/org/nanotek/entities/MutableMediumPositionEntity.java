package org.nanotek.entities;

import org.nanotek.entities.immutables.MediumPositionEntity;

public interface MutableMediumPositionEntity<K> extends MediumPositionEntity<K>{
void setMediumPosition(K k);
}
