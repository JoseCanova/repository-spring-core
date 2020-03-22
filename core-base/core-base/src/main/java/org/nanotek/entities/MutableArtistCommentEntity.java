package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistCommentEntity;

public interface MutableArtistCommentEntity<K extends Serializable> extends ArtistCommentEntity<K>{
		void setArtistComment(K k);
}
