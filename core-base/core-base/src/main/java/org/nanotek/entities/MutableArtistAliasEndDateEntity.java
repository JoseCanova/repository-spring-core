package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistAliasEndDateEntity;

public interface MutableArtistAliasEndDateEntity<K> extends ArtistAliasEndDateEntity<K>{

	void setArtistAliasEndDate(K k);
	
}
