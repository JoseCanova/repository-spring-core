package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistCreditNameEntity;

public interface MutableArtistCreditNameEntity<K> extends ArtistCreditNameEntity<K> {

	void setArtistCreditName(K k);
	
}
