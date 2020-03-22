package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistCreditEntity;

public interface MutableArtistCreditEntity<K extends Serializable> extends ArtistCreditEntity<K> {

	void setArtistCredit(K k);
	
}
