package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.CountEntity;

public interface MutableCountEntity<K> extends  CountEntity<K>{
void setCount(K k);
}
