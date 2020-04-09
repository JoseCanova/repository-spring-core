package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseAliasTypeEntity;

public interface MutableReleaseAliasTypeEntity<K> extends ReleaseAliasTypeEntity<K>{
		void setReleaseAliasType(K k);
}
