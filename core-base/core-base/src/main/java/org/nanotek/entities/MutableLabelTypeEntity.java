package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.LabelTypeEntity;

public interface MutableLabelTypeEntity<K> extends LabelTypeEntity<K>{

	void setLabelType(K k);
	
}
