package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseAliasIdEntity;

public interface MutableReleaseAliasIdEntity<K extends Serializable> extends ReleaseAliasIdEntity<K>{

	void setReleaseAliasId(K k);
	
}
