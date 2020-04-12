package org.nanotek.entities.immutables;

public interface MutableArtistNameEntity<K> extends ArtistNameEntity<K> {
void setArtistName(K k);
}
