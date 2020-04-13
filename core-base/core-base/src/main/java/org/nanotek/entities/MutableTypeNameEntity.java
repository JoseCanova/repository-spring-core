package org.nanotek.entities;

import org.nanotek.entities.immutables.TypeNameEntity;

public interface MutableTypeNameEntity<K> extends TypeNameEntity<K>{
void setTypeName(K k);
}
