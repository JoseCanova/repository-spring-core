package org.nanotek.entities;

import org.nanotek.beans.entity.ArtistAlias;
import org.nanotek.entities.immutables.ArtistAliasEntity;

public interface MutableArtistAliasEntity<K extends ArtistAlias<K>> extends ArtistAliasEntity<K extends Serializable>
{
	void setArtistAlias(K k);
}
