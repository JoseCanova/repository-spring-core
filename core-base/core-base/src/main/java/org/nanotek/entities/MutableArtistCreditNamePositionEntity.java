package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistCreditNamePositionEntity;

public interface MutableArtistCreditNamePositionEntity<T extends  Serializable> extends ArtistCreditNamePositionEntity<T>{

	void setArtistCreditNamePosition(T t);
	
}
