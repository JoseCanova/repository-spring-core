package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistCreditIdEntity;

public interface MutableArtistCreditIdEntity<K extends Serializable> extends ArtistCreditIdEntity<K>{

	void setArtistCreditId(K k);
	
}
