package org.nanotek.entities;

import org.nanotek.entities.immutables.MediumIdEntity;

public interface MutableMediumIdEntity<T> extends MediumIdEntity<T>{
void setMediumId(T t);
}
