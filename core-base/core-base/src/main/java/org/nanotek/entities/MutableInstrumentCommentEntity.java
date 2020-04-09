package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.InstrumentCommentEntity;

public interface MutableInstrumentCommentEntity<T> extends InstrumentCommentEntity<T>{
  void setInstrumentComment(T t);
}
