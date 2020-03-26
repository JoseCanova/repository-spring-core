package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleasePackagingEntity;

public interface MutableReleasePackagingEntity<T extends Serializable> extends ReleasePackagingEntity<T> {
void setReleasePackaging(T t);
}
