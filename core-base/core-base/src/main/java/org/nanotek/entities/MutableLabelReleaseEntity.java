package org.nanotek.entities;

import org.nanotek.entities.immutables.LabelReleaseEntity;

public interface MutableLabelReleaseEntity<K> extends LabelReleaseEntity<K> {
 void setLabelRelease(K k);
}
