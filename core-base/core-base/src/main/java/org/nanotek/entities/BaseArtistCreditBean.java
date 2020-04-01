package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistCreditBean;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.entities.immutables.ArtistCreditIdEntity;

public interface BaseArtistCreditBean
<K extends BaseBean<K,ArtistCredit<?>>> 
extends 
Base<K>,
BaseBean<K,ArtistCredit<?>>,
MutableArtistCreditIdEntity<Long>,
MutableNameEntity<String>,
MutableArtistCreditCountEntity<BaseArtistCreditCountBean<?>>,
MutableArtistCreditRefCountEntity<BaseArtistCreditRefCountBean<?>>
{
	@Override
	default String getName() {
		return read(NameEntity.class).map(n ->String.class.cast(n)).orElse("");
	}
	
	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}
	
	@Override
	default void setArtistCreditId(Long k) {
		write(MutableArtistCreditIdEntity.class,k);
	}
	
	@Override
	default Long getArtistCreditId() {
		return read(ArtistCreditIdEntity.class).map(c->Long.class.cast(c)).orElse(Long.MIN_VALUE);
	}
	
	public static void main(String[] args) {
		ArtistCreditBean<?> bean = new ArtistCreditBean<>();
		bean.setName("this is a name");
		System.out.println(bean.getName());
		bean.setArtistCreditId(1000L);
		System.out.println(bean.getArtistCreditId());
	}

}
