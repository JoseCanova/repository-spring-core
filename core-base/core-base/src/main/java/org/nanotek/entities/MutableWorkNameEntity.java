package org.nanotek.entities;

import org.nanotek.entities.immutables.WorkNameEntity;

public interface MutableWorkNameEntity<K> extends  WorkNameEntity<K>{
void setWorkName(K k);
}
