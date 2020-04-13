package org.nanotek.entities;

import org.nanotek.entities.immutables.MediumFormatEntity;

public interface MutableMediumFormatEntity<K> extends  MediumFormatEntity<K>{
void setMediumFormat(K k);
}
