package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistCreditCountEntity;

public interface MutableArtistCreditCountEntity<K> extends ArtistCreditCountEntity<K>{

	void setArtistCreditCount(K k);
	
	
}
