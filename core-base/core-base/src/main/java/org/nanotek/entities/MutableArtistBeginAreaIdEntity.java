package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistBeginAreaIdEntity;

public interface MutableArtistBeginAreaIdEntity<K> extends ArtistBeginAreaIdEntity<K>{
		void setAreaId(K k);
}
