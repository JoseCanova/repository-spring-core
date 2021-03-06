package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistAliasLocaleEntity;

public interface MutableArtistAliasLocaleEntity<K> extends ArtistAliasLocaleEntity<K>{

	void setArtistAliasLocale(K k);
	
}
