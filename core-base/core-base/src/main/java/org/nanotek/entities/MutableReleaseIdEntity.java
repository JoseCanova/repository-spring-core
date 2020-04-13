package org.nanotek.entities;

import java.io.Serializable;

public interface MutableReleaseIdEntity<K> {

	K getReleaseId();
	
	void setReleaseId(K k);
	
}
