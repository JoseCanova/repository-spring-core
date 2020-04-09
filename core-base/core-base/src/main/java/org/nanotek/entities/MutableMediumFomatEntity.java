package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.MediumFomatEntity;

public interface MutableMediumFomatEntity<K> extends MediumFomatEntity<K>{
void setMediumFomat(K k);
}
