package org.nanotek.entities;

import org.nanotek.entities.immutables.DiscIdEntity;

public interface MutableDiscIdEntity<T> extends DiscIdEntity<T>{
void setDiscId(T t);
}
