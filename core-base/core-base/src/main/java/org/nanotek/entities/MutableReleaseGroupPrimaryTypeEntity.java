package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseGroupPrimaryTypeEntity;

public interface MutableReleaseGroupPrimaryTypeEntity
<K> extends ReleaseGroupPrimaryTypeEntity<K>{
	void setReleaseGroupPrimaryType(K k);
}
