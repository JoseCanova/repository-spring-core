package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseLabelNumberEntity;

public interface MutableReleaseLabelNumberEntity<K> extends ReleaseLabelNumberEntity<K>{
void setReleaseLabelNumber(K k);
}
