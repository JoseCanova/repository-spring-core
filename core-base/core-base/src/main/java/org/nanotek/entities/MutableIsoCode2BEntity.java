package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.IsoCode2BEntity;

public interface MutableIsoCode2BEntity<K extends Serializable> extends IsoCode2BEntity<K>{
		void setIsoCode2B(K k);
}
