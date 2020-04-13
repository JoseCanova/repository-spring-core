package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.EndMonthEntity;

public interface MutableEndMonthEntity<T> extends EndMonthEntity<T>{
 void setEndMonth(T t);
}
