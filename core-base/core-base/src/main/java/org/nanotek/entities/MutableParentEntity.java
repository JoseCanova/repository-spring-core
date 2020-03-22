package org.nanotek.entities;

import java.io.Serializable;

public interface MutableParentEntity<K extends Serializable> extends ParentEntity<K>{
		void setParent(K k);
}
