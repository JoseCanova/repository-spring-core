package org.nanotek.entities;

public interface MutableParentEntity<K> extends ParentEntity<K>{
		void setParent(K k);
}
