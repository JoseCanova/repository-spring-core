package org.nanotek.entities;

import org.nanotek.entities.immutables.ArtistCreditRefCountEntity;

public interface MutableArtistCreditRefCountEntity<K> extends ArtistCreditRefCountEntity<K>{
	
	void setArtistCreditRefCount(K k);

}
