package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistCreditRefCount;
import org.nanotek.entities.immutables.CountEntity;

public interface BaseArtistCreditRefCountBean
<K extends BaseBean<K,ArtistCreditRefCount<?>>> 
extends
Base<K>,
BaseBean<K,ArtistCreditRefCount<?>>,
MutableCountEntity<Long>{

	@Override
	default Long getCount() {
		return read(CountEntity.class).map(c->Long.class.cast(c)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setCount(Long k) {
		write(MutableCountEntity.class,k);
	}
}
