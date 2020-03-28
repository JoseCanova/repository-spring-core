package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistAreaIdEntity;

public interface MutableArtistAreaIdEntity<K extends Serializable> extends ArtistAreaIdEntity<K> {
void setAreaId(K k);
}
