package org.nanotek.entities;

import org.nanotek.entities.immutables.MediumCountEntity;

public interface MutableMediumCountEntity<K> extends MediumCountEntity<K>{
void setMediumCount(K k);
}
