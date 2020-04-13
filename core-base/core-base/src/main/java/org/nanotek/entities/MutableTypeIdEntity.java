package org.nanotek.entities;

import java.io.Serializable;

public interface MutableTypeIdEntity<K> extends TypeIdEntity<K> {
			void setTypeId(K k);
}
