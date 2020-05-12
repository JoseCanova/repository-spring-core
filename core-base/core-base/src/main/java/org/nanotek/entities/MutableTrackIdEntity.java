package org.nanotek.entities;

import org.nanotek.entities.immutables.TrackIdEntity;

public interface MutableTrackIdEntity<T> extends TrackIdEntity<T>{
void setTrackId(T t);
}
