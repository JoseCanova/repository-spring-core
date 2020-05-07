package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistAliasBean;
import org.nanotek.beans.entity.ArtistAlias;
import org.nanotek.entities.immutables.AliasIdEntity;
import org.nanotek.entities.immutables.ArtistAliasNameEntity;


public interface BaseArtistAliasBean
<K extends BaseBean<K,ArtistAlias<?>>>
extends BaseBean<K,ArtistAlias<?>>,
Base<K>,
MutableAliasIdEntity<Long>,
MutableSortNameEntity<String>,
MutableArtistIdEntity<Long> , 
MutableTypeIdEntity<Long> , 
MutableLocaleEntity<String>,
MutableArtistAliasNameEntity<String>,
MutableBeginYearEntity<Integer>,
MutableBeginMonthEntity<Integer>,
MutableBeginDayEntity<Integer>,
MutableEndYearEntity<Integer>,
MutableEndMonthEntity<Integer>,
MutableEndDayEntity<Integer>,
MutableArtistAliasSortNameEntity<BaseArtistAliasSortNameBean<?>>,
MutableArtistEntity<BaseArtistBean<?>>, 
MutableArtistAliasTypeEntity<BaseArtistAliasTypeBean<?>> , 
MutableArtistAliasLocaleEntity<BaseArtistAliasLocaleBean<?>>,
MutableArtistAliasBeginDateEntity<BaseArtistAliasBeginDateBean<?>>,
MutableArtistAliasEndDateEntity<BaseArtistAliasEndDateBean<?>>
{
	@Override
	default Long getAliasId() {
		return read(AliasIdEntity.class).map(v->Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setAliasId(Long k) {
		write(MutableAliasIdEntity.class,k);
	}
	
	@Override
	default Long getArtistId() {
		return getArtist().getArtistId();
	}
	
	@Override
	default void setArtistId(Long k) {
		 getArtist().setArtistId(k);
	}
	
	@Override
	default Long getTypeId() {
		return getArtistAliasType().getTypeId();
	}
	
	@Override
	default void setTypeId(Long k) {
		Long typeId = k == null ? 3L : k;
		getArtistAliasType().setTypeId(typeId);
	}

	@Override
	default Integer getBeginDay() {
		return getArtistAliasBeginDate().getBeginDay();
	}
	
	@Override
	default void setBeginDay(Integer k) {
		getArtistAliasBeginDate().setBeginDay(k);
	}
	
	@Override
	default Integer getBeginMonth() {
		return getArtistAliasBeginDate().getBeginMonth();
	}
	
	@Override
	default void setBeginMonth(Integer k) {
		getArtistAliasBeginDate().setBeginMonth(k);
	}
	
	
	@Override
	default Integer getBeginYear() {
		return getArtistAliasBeginDate().getBeginYear();
	}
	
	@Override
	default void setBeginYear(Integer k) {
		getArtistAliasBeginDate().setBeginYear(k);
	}
	
	@Override
	default Integer getEndDay() {
		return getArtistAliasEndDate().getEndDay();
	}
	
	@Override
	default void setEndDay(Integer t) {
		getArtistAliasEndDate().setEndDay(t);
	}
	
	@Override
	default Integer getEndMonth() {
		return getArtistAliasEndDate().getEndMonth();
	}
	
	@Override
	default Integer getEndYear() {
		return getArtistAliasEndDate().getEndYear();
	}
	
	@Override
	default void setEndYear(Integer t) {
		getArtistAliasEndDate().setEndYear(t);
	}
	
	@Override
	default void setEndMonth(Integer t) {
		getArtistAliasEndDate().setEndMonth(t);
	}
	
	@Override
	default String getArtistAliasName() {
		return read(ArtistAliasNameEntity.class).map(n->String.class.cast(n)).orElse("");
	}
	
	@Override
	default void setArtistAliasName(String k) {
		write(MutableArtistAliasNameEntity.class,k);
	}
	
	@Override
	default void setLocale(String k) {
		getArtistAliasLocale().setLocale(k);
	}
	
	@Override
	default String getLocale() {
		return getArtistAliasLocale().getLocale();
	}
	
	
	@Override
	default String getSortName() {
		return getArtistAliasSortName().getSortName();
	}
	
	@Override
	default void setSortName(String k) {
		getArtistAliasSortName().setSortName(k);
	}
	
	
	public static void main(String[] args) {
		ArtistAliasBean bean = new ArtistAliasBean();
		bean.setArtistAliasName("this is a name");
		System.out.println(bean.getArtistAliasName());
		bean.setBeginDay(10);
		System.out.println(bean.getBeginDay());
	}
	
	
	
}
