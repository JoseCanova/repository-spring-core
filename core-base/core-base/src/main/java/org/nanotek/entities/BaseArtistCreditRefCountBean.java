package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistCreditRefCountBean;
import org.nanotek.beans.entity.ArtistCreditRefCount;
import org.nanotek.entities.immutables.CountEntity;

public interface BaseArtistCreditRefCountBean
<K extends BaseBean<K,ArtistCreditRefCount<?>>> 
extends
Base<K>,
BaseBean<K,ArtistCreditRefCount<?>>,
MutableCountEntity<Long>,
MutableRefCountEntity<Long>{

	@Override
	default Long getCount() {
		return read(CountEntity.class).map(c->Long.class.cast(c)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setCount(Long k) {
		write(MutableCountEntity.class,k);
	}
	
	@Override
	default void setRefCount(Long k) {
		setCount(k);
	}
	
	@Override
	default Long getRefCount() {
		return getCount();
	}
	
	public static void main(String[] args) {
		ArtistCreditRefCountBean<?> bean = new ArtistCreditRefCountBean<>();
		bean.setCount(1000L);
		System.out.println(bean.getCount());
		System.out.println(bean.getCount());
	}
}
