package org.nanotek.entities;

import org.nanotek.entities.immutables.WorkTypeEntity;

public interface MutableWorkTypeEntity<K> extends WorkTypeEntity<K>{
void setWorkType(K k);
}
