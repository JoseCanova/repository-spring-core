package org.nanotek.entities;

import org.nanotek.entities.immutables.LabelNameEntity;

public interface MutableLabelNameEntity<K> extends LabelNameEntity<K>{
void setLabelName(K k);
}
