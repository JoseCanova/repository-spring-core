package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistCreditRefCountEntity;

public interface MutableArtistCreditRefCountEntity<K extends Serializable> extends ArtistCreditRefCountEntity<K>{
	
	void setArtistCreditRefCount(K k);

}
