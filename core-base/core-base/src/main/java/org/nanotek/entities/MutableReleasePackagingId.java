package org.nanotek.entities;

import java.io.Serializable;

public interface MutableReleasePackagingId<K> {
	K getReleasePackagingId();
	
	void setReleasePackagingId(K k);
}
