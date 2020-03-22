package org.nanotek.entities;

import java.io.Serializable;

public interface MutableTypeIdEntity<K extends Serializable> extends TypeIdEntity<K> {
			void setTypeId(K k);
}
