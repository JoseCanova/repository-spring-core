package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.AliasIdEntity;

public interface MutableAliasIdEntity<K> 
extends AliasIdEntity<K>{

	void setAliasId(K k);
	
}
