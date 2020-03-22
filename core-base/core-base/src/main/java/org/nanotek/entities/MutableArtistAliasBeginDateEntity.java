package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistAliasBeginDateEntity;

public interface MutableArtistAliasBeginDateEntity<K extends Serializable> extends ArtistAliasBeginDateEntity<K>{

	void setArtistAliasBeginDate(K k);
	
}
