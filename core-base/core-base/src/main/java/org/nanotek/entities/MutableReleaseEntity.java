package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.ReleaseEntity;

public interface MutableReleaseEntity<K extends Serializable> extends ReleaseEntity<K>{

	void setRelease(K k);
	
}
