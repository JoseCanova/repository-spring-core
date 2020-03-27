package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.entities.immutables.ArtistIdEntity;

public interface BaseArtistBean
<K extends BaseBean<K,Artist<?>>> extends 
Base<K>,
BaseBean<K,Artist<?>>,
MutableArtistIdEntity<Long>,
MutableGidEntity<String>,
MutableNameEntity<String>,
MutableBeginDateYearEntity<Integer>,
MutableBeginDateMonthEntity<Integer>,
MutableBeginDateDayEntity<Integer>,
MutableEndDateYearEntity<Integer>,
MutableEndDateMonthEntity<Integer>,
MutableEndDateDayEntity<Integer>,
MutableTypeEntity<Long>,
MutableAreaEntity<Long>,
MutableGenderEntity<Long>,
MutableCommentEntity<String>,
MutableArtistBeginAreaEntity<Long>,
MutableArtistEndAreaEntity<Long>
{

	@Override
	default Long getArtistId() {
		return read(MutableArtistIdEntity.class).map(m->Long.class.cast(m)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setArtistId(Long k) {
		write(ArtistIdEntity.class,k);
	}
	
}
