package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistIdEntity;

public interface MutableArtistIdEntity<K> extends ArtistIdEntity<K> 
{

	void setArtistId(K k);

}
