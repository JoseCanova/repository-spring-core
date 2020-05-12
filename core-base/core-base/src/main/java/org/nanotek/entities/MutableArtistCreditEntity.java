package org.nanotek.entities;

import org.nanotek.entities.immutables.ArtistCreditEntity;

public interface MutableArtistCreditEntity<K> extends ArtistCreditEntity<K> {

	void setArtistCredit(K k);
	
}
