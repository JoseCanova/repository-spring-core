package org.nanotek.entities;

import org.nanotek.entities.immutables.RefCountEntity;

public interface MutableRefCountEntity<K> extends RefCountEntity<K>{
 void setRefCount(K k);
}
