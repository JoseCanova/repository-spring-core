package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface AliasIdEntity<K extends Serializable> {

	K getAliasId();
	
}
