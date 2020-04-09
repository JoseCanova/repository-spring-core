package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistEndDateEntity;

public interface MutableArtistEndDateEntity<K> extends ArtistEndDateEntity<K>{

	void setArtistEndDate(K k);
	
}
