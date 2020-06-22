package org.nanotek.entities;

import org.nanotek.entities.immutables.TransitionsEntity;

public interface MutableTransitionsEntity<T> extends TransitionsEntity<T>{
void setTransitions(T t);
}
