package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistBeginDateBean;
import org.nanotek.beans.entity.ArtistBeginDate;
import org.nanotek.entities.immutables.BeginYearEntity;

public interface BaseArtistBeginDateBean
<K extends BaseBean<K,ArtistBeginDate<?>>>
extends 
Base<K>,
BaseBean<K,ArtistBeginDate<?>>,
MutableBeginDateEntity<Integer> {

	
	@Override
	default void setBeginYear(Integer k) {
		write(MutableBeginYearEntity.class,k);
	}

	@Override
	default Integer getBeginYear() {
		return read(BeginYearEntity.class).map(y -> Integer.class.cast(y)).orElse(Integer.MIN_VALUE);
	}

	@Override
	default void setBeginMonth(Integer k) {
		write(MutableBeginMonthEntity.class,k);
	}

	@Override
	default Integer getBeginMonth() {
		return read(BeginMonthEntity.class).map(m->Integer.class.cast(m)).orElse(Integer.MIN_VALUE);
	}

	@Override
	default void setBeginDay(Integer k) {
		write(MutableBeginDayEntity.class,k);
	}

	@Override
	default Integer getBeginDay() {
		return read(BeginDayEntity.class).map(d->Integer.class.cast(d)).orElse(Integer.MIN_VALUE);
	}
	
	public static void main(String[] args) { 
		ArtistBeginDateBean<?> bean = new ArtistBeginDateBean<>();
		bean.setBeginDay(100);
		System.out.println(bean.getBeginDay());
		bean.setBeginMonth(100);
		System.out.println(bean.getBeginMonth());
		bean.setBeginYear(200);
		System.out.println(bean.getBeginYear());
	}
	
}
