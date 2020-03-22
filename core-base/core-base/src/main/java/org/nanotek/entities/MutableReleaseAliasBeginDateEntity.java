package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseAliasBeginDateEntity;

public interface MutableReleaseAliasBeginDateEntity<K extends Serializable> extends ReleaseAliasBeginDateEntity<K>{

	void setReleaseAliasBeginDateEntity(K k);
	
}
