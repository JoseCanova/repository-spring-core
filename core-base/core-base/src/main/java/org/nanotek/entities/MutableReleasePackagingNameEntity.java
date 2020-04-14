package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleasePackaginNameEntity;

public interface MutableReleasePackagingNameEntity<K> extends ReleasePackaginNameEntity<K>{

	void setReleasePackagingName(K name);
	
}
