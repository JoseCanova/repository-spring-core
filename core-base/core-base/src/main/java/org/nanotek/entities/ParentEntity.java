package org.nanotek.entities;

import java.io.Serializable;

public interface ParentEntity<K extends Serializable> {
		K getParent();
}
