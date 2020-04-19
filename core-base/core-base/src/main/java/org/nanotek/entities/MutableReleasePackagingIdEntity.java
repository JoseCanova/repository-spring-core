package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleasePackagingIdEntity;

public interface MutableReleasePackagingIdEntity<K> extends ReleasePackagingIdEntity<K>{
	void setReleasePackagingId(K k);
}
