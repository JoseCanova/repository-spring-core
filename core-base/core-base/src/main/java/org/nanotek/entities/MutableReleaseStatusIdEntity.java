package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseStatusIdEntity;

public interface MutableReleaseStatusIdEntity<T extends Serializable> extends ReleaseStatusIdEntity<T>{
void setReleaseStatusId(T t);
}
