package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ReleaseAliasBeginDateBean;
import org.nanotek.beans.entity.ReleaseAliasBeginDate;
import org.nanotek.entities.immutables.BeginYearEntity;

public interface BaseReleaseAliasBeginDateBean
<K extends BaseBean<K,ReleaseAliasBeginDate<?>>> 
extends Base<K>,
BaseBean<K,ReleaseAliasBeginDate<?>>,
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
		BaseReleaseAliasBeginDateBean bean = new ReleaseAliasBeginDateBean<>();
		bean.setBeginYear(2000);
		System.out.println(bean.getBeginYear());
		bean.setBeginMonth(2000);
		System.out.println(bean.getBeginMonth());
		bean.setBeginDay(2000);
		System.out.println(bean.getBeginDay());
	}
}
