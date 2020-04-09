package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistEndAreaEntity;

public interface MutableArtistEndAreaEntity<T> extends  ArtistEndAreaEntity<T>{

	void setEndArea(T t);
	
}
