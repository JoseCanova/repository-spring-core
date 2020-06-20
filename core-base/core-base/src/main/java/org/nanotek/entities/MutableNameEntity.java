package org.nanotek.entities;

public interface MutableNameEntity<K> extends  NameEntity<K>{

	void setName(K k);
}
