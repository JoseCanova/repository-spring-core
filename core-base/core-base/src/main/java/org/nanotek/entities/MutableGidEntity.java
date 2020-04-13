package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.GidEntity;

public interface MutableGidEntity<K> extends GidEntity<K> {
	void setGid(K k);
}
