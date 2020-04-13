package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistEntity;

public interface MutableArtistEntity<K> extends ArtistEntity<K>{
   
	void setArtist(K e);
	
	
}
