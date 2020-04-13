package org.nanotek.entities;

import org.nanotek.entities.immutables.ArtistCreditNamePositionEntity;

public interface MutableArtistCreditNamePositionEntity<T> extends ArtistCreditNamePositionEntity<T>{

	void setArtistCreditNamePosition(T t);
	
}
