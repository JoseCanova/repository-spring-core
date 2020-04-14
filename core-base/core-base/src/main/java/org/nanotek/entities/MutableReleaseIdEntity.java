package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseIdEntity;

public interface MutableReleaseIdEntity<K> extends ReleaseIdEntity<K>{

	void setReleaseId(K k);
	
}
