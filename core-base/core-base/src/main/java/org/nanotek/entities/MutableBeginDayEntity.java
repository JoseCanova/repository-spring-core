package org.nanotek.entities;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Qualifier;

public interface MutableBeginDayEntity<K extends Serializable> extends BeginDayEntity<K>{
  @Qualifier(value="default")
  void setBeginDay(K k);
}
