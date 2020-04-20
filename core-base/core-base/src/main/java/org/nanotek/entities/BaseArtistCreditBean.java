package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistCreditBean;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.entities.immutables.ArtistCreditIdEntity;
import org.nanotek.entities.immutables.ArtistCreditNameEntity;

public interface BaseArtistCreditBean
<K extends BaseBean<K,ArtistCredit<?>>> 
extends 
Base<K>,
BaseBean<K,ArtistCredit<?>>,
MutableArtistCreditIdEntity<Long>,
MutableArtistCreditNameEntity<String>,
MutableArtistCreditCountEntity<BaseArtistCreditCountBean<?>>,
MutableArtistCreditRefCountEntity<BaseArtistCreditRefCountBean<?>>
{
	@Override
	default String getArtistCreditName() {
		return read(ArtistCreditNameEntity.class).map(n ->String.class.cast(n)).orElse("");
	}
	
	@Override
	default void setArtistCreditName(String k) {
		write(MutableArtistCreditNameEntity.class,k);
	}
	
	@Override
	default void setArtistCreditId(Long k) {
		write(MutableArtistCreditIdEntity.class,k);
	}
	
	@Override
	default Long getArtistCreditId() {
		return read(ArtistCreditIdEntity.class).map(c->Long.class.cast(c)).orElse(Long.MIN_VALUE);
	}
	
	default Long getCount() {
		return getArtistCreditCount().getCount();
	}
	
	default void setCount(Long l) {
		getArtistCreditCount().setCount(l);
	}
	
	default Long getRefCount() {
		return getArtistCreditRefCount().getRefCount();
	}
	
	default void setRefCount(Long l) {
		getArtistCreditRefCount().setRefCount(l);
	}
	
	public static void main(String[] args) {
		ArtistCreditBean<?> bean = new ArtistCreditBean<>();
		bean.setArtistCreditName("this is a name");
		System.out.println(bean.getArtistCreditName());
		bean.setArtistCreditId(1000L);
		System.out.println(bean.getArtistCreditId());
	}

}
