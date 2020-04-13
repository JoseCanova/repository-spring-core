package org.nanotek.entities;

import org.nanotek.entities.immutables.RecordingAliasNameEntity;

public interface MutableRecordingAliasNameEntity<K> extends RecordingAliasNameEntity<K>{
void setRecordingAliasName(K k);
}
