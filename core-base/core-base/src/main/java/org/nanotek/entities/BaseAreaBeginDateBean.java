package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.AreaBeginDateBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.entities.immutables.BeginYearEntity;

public interface BaseAreaBeginDateBean<K extends BaseBean<K,AreaBeginDate<?>>>
extends 
Base<K>,
BaseBean<K,AreaBeginDate<?>>,
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
}
