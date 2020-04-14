package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseLabelEntity;

public interface MutableReleaseLabelEntity<K> extends ReleaseLabelEntity<K>{
void setReleaseLabel(K k);
}
