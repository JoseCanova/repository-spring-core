package org.nanotek.entities;

import org.nanotek.entities.immutables.TrackPositionEntity;

public interface MutableTrackPositionEntity<T> extends TrackPositionEntity<T>{
void setTrackPosition(T t);
}
