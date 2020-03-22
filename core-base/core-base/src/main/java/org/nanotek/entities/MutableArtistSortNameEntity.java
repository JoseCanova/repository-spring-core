package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistSortNameEntity;

public interface MutableArtistSortNameEntity<K extends Serializable> extends ArtistSortNameEntity<K> {

	void setArtistSortName(K k);
	
}
