package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistBeginAreaEntity;

public interface MutableArtistBeginAreaEntity<K extends Serializable> extends ArtistBeginAreaEntity<K>{
	 void setBeginArea(K k);
}
