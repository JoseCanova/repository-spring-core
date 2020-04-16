package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaEndDate;
import org.nanotek.entities.immutables.EndDayEntity;
import org.nanotek.entities.immutables.EndMonthEntity;
import org.nanotek.entities.immutables.EndYearEntity;

public interface BaseAreaEndDateBean
<K extends BaseBean<K,AreaEndDate<?>>>
extends  
Base<K>,
BaseBean<K,AreaEndDate<?>>,
MutableEndDateEntity<Integer>,
MutableEndYearEntity<Integer>,MutableEndMonthEntity<Integer>,MutableEndDayEntity<Integer>{

	
	@Override
	default void setEndYear(Integer t) {
		write(MutableEndYearEntity.class,t);
	}
	
	@Override
	default Integer getEndYear() {
		return read(EndYearEntity.class).map(y ->Integer.class.cast(y)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setEndMonth(Integer t) {
		write(MutableEndMonthEntity.class,t);
	}
	
	@Override
	default Integer getEndMonth() {
		return read(EndMonthEntity.class).map(m ->Integer.class.cast(m)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setEndDay(Integer t) {
		write(MutableEndDayEntity.class,t);
	}
	
	@Override
	default Integer getEndDay() {
		return read(EndDayEntity.class).map(d -> Integer.class.cast(d)).orElse(Integer.MIN_VALUE);
	}
}
