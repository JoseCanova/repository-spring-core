package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.MediumEntity;

public interface MutableMediumEntity<K extends Serializable> extends MediumEntity<K>{
void setMedium(K k);
}
