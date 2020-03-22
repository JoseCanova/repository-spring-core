package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface ArtistEntity<K extends Serializable> {

	K getArtist();
	
}
