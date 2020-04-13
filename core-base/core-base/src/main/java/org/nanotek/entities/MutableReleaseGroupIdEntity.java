package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseGroupIdEntity;

public interface MutableReleaseGroupIdEntity<K> extends ReleaseGroupIdEntity<K> {

	

	
	void setReleaseGroupId(K k);
}
