package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.PropertyChangeSupportEntity;

public interface MutableNameEntity<K extends Serializable> extends  NameEntity<K>{

	void setName(K k);
}
