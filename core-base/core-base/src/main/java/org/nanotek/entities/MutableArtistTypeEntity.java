package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistTypeEntity;

public interface MutableArtistTypeEntity<K extends Serializable> extends ArtistTypeEntity<K>{
				void setArtistType(K k);
}
