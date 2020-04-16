package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseLabelIdEntity;

public interface MutableReleaseLabelIdEntity<K> extends ReleaseLabelIdEntity<K>{
void setReleaseLabelId(K k);
}
