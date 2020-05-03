package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseStatusEntity;

public interface MutableReleaseStatusEntity<T> extends ReleaseStatusEntity<T> {
void setReleaseStatus(T t);
}
