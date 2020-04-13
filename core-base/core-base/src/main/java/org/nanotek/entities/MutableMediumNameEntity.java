package org.nanotek.entities;

import org.nanotek.entities.immutables.MediumNameEntity;

public interface MutableMediumNameEntity<K> extends MediumNameEntity<K>{
void setMediumName(K k);
}
