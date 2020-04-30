package org.nanotek.entities;

import org.nanotek.entities.immutables.RecordingIsrcsEntity;

public interface MutableRecordingIsrcSetEntity<T> extends  RecordingIsrcsEntity<T>{
void setRecordingIsrcs(T isrcs);
}
