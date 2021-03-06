package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistAliasTypeEntity;

public interface MutableArtistAliasTypeEntity<K> extends ArtistAliasTypeEntity<K> {

	void setArtistAliasType(K k);
	
}
