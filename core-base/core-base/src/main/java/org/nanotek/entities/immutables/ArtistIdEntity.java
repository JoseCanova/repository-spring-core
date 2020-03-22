package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface ArtistIdEntity<K extends Serializable> {

	K getArtistId();
	
}
