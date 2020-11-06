package org.nanotek.collections;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.entities.MutableArtistIdEntity;
import org.nanotek.entities.immutables.ArtistIdEntity;

interface ArtistIdentity
<K extends BaseBean<K,Artist<?>>>  
extends 
Base<K>,
BaseBean<K,Artist<?>>,
MutableArtistIdEntity<Long>{
		
		@Override
		default Long getArtistId() {
			return read(ArtistIdEntity.class).filter(v->v!=null).map(v -> Long.class.cast(v)).orElse(Long.MIN_VALUE);
		}
		
		@Override
		default void setArtistId(Long k) {
			write(MutableArtistIdEntity.class , k);
		}
		
}