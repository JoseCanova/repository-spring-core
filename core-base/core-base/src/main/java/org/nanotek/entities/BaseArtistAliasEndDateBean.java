package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistAliasEndDate;
import org.nanotek.entities.immutables.EndDayEntity;
import org.nanotek.entities.immutables.EndMonthEntity;
import org.nanotek.entities.immutables.EndYearEntity;
import org.nanotek.opencsv.ArtistAliasEndDateBean;

public interface BaseArtistAliasEndDateBean
<K extends BaseBean<K,ArtistAliasEndDate<?>>> 
extends Base<K>,
BaseBean<K,ArtistAliasEndDate<?>>,
MutableEndYearEntity<Integer>,
MutableEndMonthEntity<Integer>,
MutableEndDayEntity<Integer>
{
  
	@Override
	default Integer getEndYear() {
		return read(EndYearEntity.class).map(y -> Integer.class.cast(y)).orElse(Integer.MIN_VALUE);
	}
	
	default void setEndYear(Integer k) { 
		write(MutableEndYearEntity.class,k);
	}
	
	@Override
	default Integer getEndMonth() {
		return read(EndMonthEntity.class).map(m ->Integer.class.cast(m)).orElse(Integer.MIN_VALUE);
	}
	
	
	@Override
	default void setEndMonth(Integer t) {
		write(MutableEndMonthEntity.class,t);
	}
	
	@Override
	default Integer getEndDay() {
		return read(EndDayEntity.class).map(d -> Integer.class.cast(d)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setEndDay(Integer t) {
		write(MutableEndDayEntity.class,t);
	}
	
	
	public  static void main (String[] args) {
		ArtistAliasEndDateBean bean = new ArtistAliasEndDateBean();
		bean.setEndYear(2000);
		System.out.println(bean.getEndYear());
		bean.setEndMonth(2000);
		System.out.println(bean.getEndMonth());
		bean.setEndDay(2000);
		System.out.println(bean.getEndDay());
	}
}
