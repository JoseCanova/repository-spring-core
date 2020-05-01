package org.nanotek.entities;

import org.nanotek.entities.immutables.EntityClassNameEntity;

public interface MutableEntityClassNameEntity<K> extends EntityClassNameEntity<K>{
void setEntityClassName(K k);
}
