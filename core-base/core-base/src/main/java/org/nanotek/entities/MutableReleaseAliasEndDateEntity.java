package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseAliasEndDateEntity;

public interface MutableReleaseAliasEndDateEntity<K> extends ReleaseAliasEndDateEntity<K>{

	void setReleaseAliasEndDate(K k);
}
