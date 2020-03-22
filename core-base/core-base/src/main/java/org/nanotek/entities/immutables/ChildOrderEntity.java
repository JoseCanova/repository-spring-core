package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface ChildOrderEntity<K extends Serializable> {
	K getChildOrder();
}
