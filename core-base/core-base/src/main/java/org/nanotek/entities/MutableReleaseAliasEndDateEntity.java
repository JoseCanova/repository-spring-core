package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseAliasEndDateEntity;

public interface MutableReleaseAliasEndDateEntity<K extends Serializable> extends ReleaseAliasEndDateEntity<K>{

	void setReleaseAliasEndDate(K k);
}
