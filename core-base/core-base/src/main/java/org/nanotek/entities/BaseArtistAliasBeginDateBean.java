package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistAliasBeginDateBean;
import org.nanotek.beans.entity.ArtistAliasBeginDate;
import org.nanotek.entities.immutables.BeginYearEntity;

public interface BaseArtistAliasBeginDateBean
<K extends BaseBean<K,ArtistAliasBeginDate<?>>> 
extends Base<K>,
BaseBean<K,ArtistAliasBeginDate<?>>,
MutableBeginDateEntity<Integer>,
MutableBeginYearEntity<Integer>,
MutableBeginMonthEntity<Integer>,
MutableBeginDayEntity<Integer>
{
	@Override
	default Integer getBeginYear() {
		return read(BeginYearEntity.class).map(y -> Integer.class.cast(y)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setBeginYear(Integer k) {
		write(MutableBeginYearEntity.class,k);
	}
	
	@Override
	default Integer getBeginMonth() {
		return read(BeginMonthEntity.class).map(m->Integer.class.cast(m)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setBeginMonth(Integer k) {
		write(MutableBeginMonthEntity.class,k);
	}
	
	@Override
	default Integer getBeginDay() {
		return read(BeginDayEntity.class).map(d ->Integer.class.cast(d)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setBeginDay(Integer k) {
		write(MutableBeginDayEntity.class,k);
	}
	
	public static void main(String[] args) {
		ArtistAliasBeginDateBean bean = new ArtistAliasBeginDateBean<>();
		bean.setBeginYear(2000);
		System.out.println(bean.getBeginYear());
		bean.setBeginMonth(2000);
		System.out.println(bean.getBeginMonth());
		bean.setBeginDay(2000);
		System.out.println(bean.getBeginDay());
	}
}
