package org.nanotek.entities;

import org.nanotek.entities.immutables.TrackNameEntity;

public interface MutableTrackNameEntity<T> extends TrackNameEntity<T> {
void setTrackName(T t);
}
