package org.nanotek.entities;

import org.nanotek.entities.immutables.ArtistCreditedNameEntity;

public interface MutableArtistCreditedNameEntity<K> extends ArtistCreditedNameEntity<K> {
	void setArtistCreditedName(K k);
}
