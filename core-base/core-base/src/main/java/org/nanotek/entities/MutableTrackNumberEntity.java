package org.nanotek.entities;

public interface MutableTrackNumberEntity<T> extends TrackNumberEntity<T>{
void setTrackNumber (T t);
}
