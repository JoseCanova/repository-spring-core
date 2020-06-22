package org.nanotek.entities;

import org.nanotek.entities.immutables.ArtistIdEntity;

public interface MutableArtistIdEntity<K> extends ArtistIdEntity<K> 
{

	void setArtistId(K k);

}
