package org.nanotek.entities;

import org.nanotek.entities.immutables.AreaNameEntity;

public interface MutableAreaNameEntity<K> extends AreaNameEntity<K>{
void setAreaName(K k);
}
