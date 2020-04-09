package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistCreditNameIdEntity;

public interface MutatleArtistCreditNameIdEntity<K> extends ArtistCreditNameIdEntity<K> {
	void setArtistCreditNameId(K k);
}
