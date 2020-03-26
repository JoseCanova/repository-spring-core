package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseGroupEntity;

public interface MutableReleaseGroupEntity<T extends Serializable> extends ReleaseGroupEntity<T>{
void setReleaseGroup(T t);
}
