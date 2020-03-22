package org.nanotek.entities;

import java.io.Serializable;

public interface MutableReleaseIdEntity<K extends Serializable> {

	K getReleaseId();
	
	void setReleaseId(K k);
	
}
