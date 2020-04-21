package org.nanotek.entities;

import org.nanotek.entities.immutables.MediumFormatIdEntity;

public interface MutableMediumFormatIdEntity<T> extends MediumFormatIdEntity<T>{
void setMediumFormatId(T t);
}
