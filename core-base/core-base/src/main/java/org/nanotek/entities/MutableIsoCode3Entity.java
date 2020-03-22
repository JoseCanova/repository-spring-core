package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.IsoCode3Entity;

public interface MutableIsoCode3Entity<K extends Serializable> extends IsoCode3Entity<K> {
			void setIsoCode3(K k);
}
