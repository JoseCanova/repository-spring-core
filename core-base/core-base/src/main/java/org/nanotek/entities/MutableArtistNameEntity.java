package org.nanotek.entities;

import org.nanotek.entities.immutables.ArtistNameEntity;

public interface MutableArtistNameEntity<K> extends ArtistNameEntity<K> {
void setArtistName(K k);
}
