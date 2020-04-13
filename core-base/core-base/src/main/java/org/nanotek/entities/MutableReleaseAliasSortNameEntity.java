package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseAliasSortNameEntity;

public interface MutableReleaseAliasSortNameEntity<K> extends ReleaseAliasSortNameEntity<K>{
  void setReleaseAliasSortName(K k);
}
