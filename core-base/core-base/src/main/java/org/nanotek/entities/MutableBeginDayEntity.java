package org.nanotek.entities;

import org.springframework.beans.factory.annotation.Qualifier;

public interface MutableBeginDayEntity<K> extends BeginDayEntity<K>{
  @Qualifier(value="default")
  void setBeginDay(K k);
}
