package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistBeginDateEntity;

public interface MutableArtistBeginDateEntity<K extends Serializable> extends ArtistBeginDateEntity<K>{

	void setArtistBeginDate(K k);
	
}
