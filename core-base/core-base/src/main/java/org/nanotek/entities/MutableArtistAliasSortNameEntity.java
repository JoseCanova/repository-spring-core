package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistAliasSortNameEntity;

public interface MutableArtistAliasSortNameEntity<K extends Serializable> extends ArtistAliasSortNameEntity<K>{
		void setArtistAliasSortName(K k);
}
