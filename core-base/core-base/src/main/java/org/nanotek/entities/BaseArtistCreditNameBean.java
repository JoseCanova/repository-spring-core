package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistCreditNameBean;
import org.nanotek.beans.entity.ArtistCreditName;
import org.nanotek.entities.immutables.ArtistCreditNameIdEntity;

public interface BaseArtistCreditNameBean
<K extends BaseBean<K,ArtistCreditName<?>>> extends 
Base<K>,
BaseBean<K,ArtistCreditName<?>>,
MutableNameEntity<String>,
MutatleArtistCreditNameIdEntity<Long>{
	
	
	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}

	@Override
	default String getName() {
		return read(NameEntity.class).map(n->String.class.cast(n)).orElse("");
	}
	
	@Override
	default Long getArtistCreditNameId() {
		return read(ArtistCreditNameIdEntity.class).map(v->Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setArtistCreditNameId(Long k) {
		write(MutatleArtistCreditNameIdEntity.class,k);
	}
	
	public static  void main (String [] args) {
		ArtistCreditNameBean<?> bean = new ArtistCreditNameBean<>();
		bean.setName("this is a name");
		System.out.println(bean.getName());
		bean.setArtistCreditNameId(1000L);
		System.out.println(bean.getArtistCreditNameId());
		
	}
	
}
