package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.IsoCode2TEntity;

public interface MutableIsoCode2TEntity<K> extends IsoCode2TEntity<K>{
		void setIsoCode2T(K k);
}
