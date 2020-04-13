package org.nanotek.entities;

import org.nanotek.entities.immutables.RecordingAliasSortNameEntity;

public interface MutableRecordingAliasSortNameEntity<K> extends RecordingAliasSortNameEntity<K> {
void setRecordingAliasSortName(K k);
}
