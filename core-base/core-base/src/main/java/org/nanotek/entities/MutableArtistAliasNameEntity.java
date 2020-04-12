package org.nanotek.entities;

import org.nanotek.entities.immutables.ArtistAliasNameEntity;

public interface MutableArtistAliasNameEntity<K> extends ArtistAliasNameEntity<K>{
void setArtistAliasName(K k);
}
