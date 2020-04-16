package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseGroupNameEntity;

public interface MutableReleaseGroupNameEntity<K> extends ReleaseGroupNameEntity<K>{
void setReleaseGroupName(K k);
}
