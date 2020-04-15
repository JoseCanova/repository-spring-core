package org.nanotek.entities;

import org.nanotek.entities.immutables.IsrcEntity;

//TODO:implement a validator annotation.
public interface MutableIsrcEntity<K> extends IsrcEntity<K>{
void setIsrc(K k);
}
