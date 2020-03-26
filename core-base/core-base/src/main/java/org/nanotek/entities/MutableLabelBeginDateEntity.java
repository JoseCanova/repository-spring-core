package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.LabelBeginDateEntity;

public interface MutableLabelBeginDateEntity<T extends Serializable> 
extends LabelBeginDateEntity<T>{
void setLabelBeginDate(T t);
}
