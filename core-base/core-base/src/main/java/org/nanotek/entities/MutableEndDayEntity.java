package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.EndDayEntity;

public interface MutableEndDayEntity<T> extends EndDayEntity<T>{
 void setEndDay(T t);
}
