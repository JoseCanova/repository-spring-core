package org.nanotek.entities;

import java.io.Serializable;

public interface MutableBeginDayEntity<K extends Serializable> extends BeginDayEntity<K>{
  void setBeginDay(K k);
}
