package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.LabelCodeEntity;

public interface MutableLabelCodeEntity<K	> extends LabelCodeEntity<K>{
void setLabelCode(K k);
}
