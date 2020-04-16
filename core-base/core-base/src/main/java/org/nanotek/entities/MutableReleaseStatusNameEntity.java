package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseStatusNameEntity;

public interface MutableReleaseStatusNameEntity<K> extends ReleaseStatusNameEntity<K>{
void setReleaseStatusName(K k);
}
