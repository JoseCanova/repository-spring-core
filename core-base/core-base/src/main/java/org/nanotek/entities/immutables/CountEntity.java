package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface CountEntity<K extends Serializable> {
K getCount();
}
