package org.nanotek.entities;

import org.nanotek.entities.immutables.TrackLengthEntity;

public interface MutableTrackLengthEntity<T> extends TrackLengthEntity<T>{
void setTrackLength(T t);
}
