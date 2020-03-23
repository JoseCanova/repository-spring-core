package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.AliasIdEntity;

public interface MutableAliasIdEntity<K extends Serializable> extends AliasIdEntity<K extends Serializable>

	void setAliasId(K k);
	
}
