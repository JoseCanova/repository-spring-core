package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.GidEntity;
import org.nanotek.MutatorSupport;

public interface MutableGidEntity<K extends Serializable> extends GidEntity<K> {
	void setGid(K k);
}
