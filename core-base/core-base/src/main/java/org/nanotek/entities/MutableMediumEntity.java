package org.nanotek.entities;

import org.nanotek.entities.immutables.MediumEntity;

public interface MutableMediumEntity<K> 
extends MediumEntity<K>{
void setMedium(K k);
}
