package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface ArtistCreditRefCountEntity<K extends Serializable> {

	K getArtistCreditRefCount(K k);
	
}
