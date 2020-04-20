package org.nanotek.entities;

import org.nanotek.GidEntity;

public interface MutableGidEntity<K> extends GidEntity<K> {
	void setGid(K k);
}
