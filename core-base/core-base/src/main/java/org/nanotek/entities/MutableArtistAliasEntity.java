package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistAliasEntity;

public interface MutableArtistAliasEntity<K> extends ArtistAliasEntity<K>
{
	void setArtistAlias(K k);
}
