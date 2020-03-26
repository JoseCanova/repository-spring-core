package org.nanotek.entities;

import org.nanotek.entities.immutables.LabelEndDateEntity;

public interface MutableLabelEndDateEntity<T> extends LabelEndDateEntity<T> {
void setLabelEndDate(T t);
}
