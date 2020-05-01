package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseGroupEntity;

public interface MutableReleaseGroupEntity<T> extends ReleaseGroupEntity<T>{
void setReleaseGroup(T t);
}
