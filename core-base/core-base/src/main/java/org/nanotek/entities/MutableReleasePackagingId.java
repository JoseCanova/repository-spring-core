package org.nanotek.entities;

import java.io.Serializable;

public interface MutableReleasePackagingId<K extends Serializable> {
	K getReleasePackagingId();
	
	void setReleasePackagingId(K k);
}
