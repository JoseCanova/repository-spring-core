package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseAliasBeginDateEntity;

public interface MutableReleaseAliasBeginDateEntity<K> extends ReleaseAliasBeginDateEntity<K>{

	void setReleaseAliasBeginDate(K k);
	
}
