package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseEntity;

public interface MutableReleaseEntity<K> extends ReleaseEntity<K>{

	void setRelease(K k);
	
}
