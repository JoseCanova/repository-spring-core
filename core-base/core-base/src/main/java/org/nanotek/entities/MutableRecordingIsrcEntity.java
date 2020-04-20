package org.nanotek.entities;

import org.nanotek.entities.immutables.RecordingIsrcEntity;

public interface MutableRecordingIsrcEntity<K> extends RecordingIsrcEntity<K>{
void setRecordingIsrc(K k); 
}
