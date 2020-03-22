package org.nanotek.entities;

import java.io.Serializable;

public interface TypeIdEntity<K extends Serializable> {
			K getTypeId();
}
