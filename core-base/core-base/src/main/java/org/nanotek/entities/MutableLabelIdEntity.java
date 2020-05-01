package org.nanotek.entities;

import org.nanotek.entities.immutables.LabelIdEntity;

public interface MutableLabelIdEntity<T> extends LabelIdEntity<T> {
void setLabelId(Long id);
}
