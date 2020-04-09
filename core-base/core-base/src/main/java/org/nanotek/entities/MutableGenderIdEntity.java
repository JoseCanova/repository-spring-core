package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.GenderIdEntity;

public interface MutableGenderIdEntity<K> extends GenderIdEntity<K>{
void setGenderId(K k);
}
