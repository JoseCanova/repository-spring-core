package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.LabelBeginDate;
import org.nanotek.entities.immutables.BeginYearEntity;

public interface BaseLabelBeginDateBean
<K extends BaseBean<K,LabelBeginDate<?>>>
extends 
Base<K>,
BaseBean<K,LabelBeginDate<?>>,
MutableBeginDateEntity<Integer>,
MutableBeginYearEntity<Integer>,
MutableBeginMonthEntity<Integer>,
MutableBeginDayEntity<Integer>{
	
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
