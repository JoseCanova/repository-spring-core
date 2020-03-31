package org.nanotek.entities;

import org.nanotek.entities.immutables.ArtistAreaEntity;

public interface MutableArtistAreaEntity<T> extends ArtistAreaEntity<T> {
void  setArea(T t);
}
