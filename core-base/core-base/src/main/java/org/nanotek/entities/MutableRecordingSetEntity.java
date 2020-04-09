package org.nanotek.entities;

import java.io.Serializable;
import java.util.Set;

import org.nanotek.entities.immutables.RecordingSetEntity;

public interface MutableRecordingSetEntity<T> extends RecordingSetEntity<T> {
void setRecordings(Set<T> recordings);
}
