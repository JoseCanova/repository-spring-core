package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistCreditNamePositionBean;
import org.nanotek.beans.entity.ArtistCreditNamePosition;
import org.nanotek.entities.immutables.PositionEntity;

public interface BaseArtistCreditNamePositionBean
<K extends BaseBean<K,ArtistCreditNamePosition<?>>> 
extends 
Base<K>,
BaseBean<K,ArtistCreditNamePosition<?>>,
MutablePositionEntity<Long>
{
	@Override
	default Long getPosition() {
		return read(PositionEntity.class).map(p->Long.class.cast(p)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setPosition(Long k) {
		write(MutablePositionEntity.class,k);
	}
	
	public static void main(String[] args) {
		
		
		ArtistCreditNamePositionBean<?> bean = new ArtistCreditNamePositionBean<>();
		bean.setPosition(1000L);
		System.out.println(bean.getPosition());
		
	}
	
	
}
