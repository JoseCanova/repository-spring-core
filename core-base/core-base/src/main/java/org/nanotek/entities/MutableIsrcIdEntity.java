package org.nanotek.entities;

import org.nanotek.entities.immutables.IsrcIdEntity;

public interface MutableIsrcIdEntity<K> extends IsrcIdEntity<K>{
void setIsrcId(K k);
}
