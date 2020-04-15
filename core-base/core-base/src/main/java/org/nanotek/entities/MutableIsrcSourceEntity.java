package org.nanotek.entities;

import org.nanotek.entities.immutables.IsrcSourceEntity;

public interface MutableIsrcSourceEntity<K> extends IsrcSourceEntity<K> {
 void setIsrcSource(K k);
}
