package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleasePackagingNameEntity;

public interface MutableReleasePackagingNameEntity<K> extends ReleasePackagingNameEntity<K>{

	void setReleasePackagingName(K name);
	
}
