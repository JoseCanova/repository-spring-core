package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistAliasLocaleEntity;

public interface MutableArtistAliasLocaleEntity<K extends Serializable> extends ArtistAliasLocaleEntity<K>{

	void setArtistAliasLocale(K k);
	
}
