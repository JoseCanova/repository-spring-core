package org.nanotek.entities;

import org.nanotek.entities.immutables.RecordingNameEntity;

public interface MutableRecordingNameEntity<K> extends  RecordingNameEntity<K>{
void setRecordingName(K k);
}
