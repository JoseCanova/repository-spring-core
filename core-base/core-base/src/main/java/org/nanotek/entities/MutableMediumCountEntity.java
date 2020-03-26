package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.MediumCountEntity;

public interface MutableMediumCountEntity<K extends Serializable> extends MediumCountEntity<K>{
void setMediumCount(K k);
}
