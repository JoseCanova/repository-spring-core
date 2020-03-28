package org.nanotek.entities;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistBeginDate;
import org.nanotek.entities.immutables.ArtistBeginDateEntity;
import org.nanotek.entities.immutables.ArtistIdEntity;
import org.nanotek.entities.immutables.BeginYearEntity;
import org.nanotek.entities.immutables.EndDayEntity;
import org.nanotek.entities.immutables.EndMonthEntity;
import org.nanotek.entities.immutables.EndYearEntity;

public interface BaseArtistBean
<K extends BaseBean<K,Artist<?>>> extends 
Base<K>,
BaseBean<K,Artist<?>>,
MutableArtistIdEntity<Long>,
MutableGidEntity<String>,
MutableNameEntity<String>,
MutableBeginDateEntity<ArtistBeginDate<?>>,
MutableBeginDateYearEntity<Integer>,
MutableBeginDateMonthEntity<Integer>,
MutableBeginDateDayEntity<Integer>,
MutableEndDateYearEntity<Integer>,
MutableEndDateMonthEntity<Integer>,
MutableEndDateDayEntity<Integer>,
MutableTypeIdEntity<Long>,
MutableAreaEntity<Area<?>>,
MutableGenderEntity<Long>,
MutableGenderIdEntity<Long>,
MutableCommentEntity<String>,
MutableArtistAreaIdEntity<Long>,
MutableArtistBeginAreaEntity<Area<?>>,
MutableArtistEndAreaEntity<Area<?>>,
MutableTypeEntity<Long>

{

	
	default Long getArtistId() {
		return read(ArtistIdEntity.class)
				.map(m->Long.class.cast(m)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setArtistId(Long k) {
		write(MutableArtistIdEntity.class,k);
	}
	
	@Override
	default String getName() {
		return read(NameEntity.class)
				.map(m->String.class.cast(m)).orElse("");
	}
	
	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}

	@Override
	default Integer getBeginDateYear() {
		Optional<ArtistBeginDate<?>> d = read(ArtistBeginDateEntity.class).map(m ->ArtistBeginDate.class.cast(m));
		
		return d.map(d1 ->{
			return d1.getBeginYear();
		}).orElse(Integer.MIN_VALUE);
//		return read(BeginYearEntity.class)
//				.filter(y->y!=null)
//				.map(m->Integer.class.cast(m)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setBeginDateYear(Integer t) {
		Optional<ArtistBeginDate<?>> d = read(ArtistBeginDateEntity.class).map(m ->ArtistBeginDate.class.cast(m));
		d.ifPresent(d1->d1.setBeginYear(t));
	}
	
	@Override
	default Integer getBeginDateMonth() {
		return read(BeginMonthEntity.class)
				.filter(y->y!=null)
				.map(m->Integer.class.cast(m)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setBeginDateMonth(Integer t) {
				Optional.ofNullable(t).filter(t1->t1!=null).ifPresent(m -> 
								write(BeginMonthEntity.class,m));
	}
	
	@Override
	default Integer getBeginDateDay() {
		return read(BeginDayEntity.class).filter(da->da!=null).
				map(d->Integer.class.cast(d)).orElse(Integer.MIN_VALUE);
	}
	
	
	@Override
	default void setEndDateYear(Integer t) {
		Optional.ofNullable(t).ifPresent(y->
		write(EndYearEntity.class,y));
	}
	
	@Override
	default Integer getEndDateYear() {
		return read(EndYearEntity.class)
				.filter(y->y!=null)
				.map(y1->Integer.class.cast(y1))
				.orElse(Integer.MIN_VALUE);
	}
	
	
	@Override
	default Integer getEndDateMonth() {
		return read(EndMonthEntity.class)
				.filter(m->m!=null)
				.map(m1->Integer.class.cast(m1))
				.orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setEndDateMonth(Integer t) {
		Optional.ofNullable(t).ifPresent(m->
				write(EndMonthEntity.class,t)
		);
	}

	@Override
	default Integer getEndDateDay() {
		return read(EndDayEntity.class)
				.filter(d -> d!=null)
				.map(d1->Integer.class.cast(d1))
				.orElse(Integer.MIN_VALUE);
	}

	
	@Override
	default Long getGender() {
		return read(GenderEntity.class).map(g->Long.class.cast(g)).orElse(Long.MIN_VALUE);
	}

	@Override
	default void setGender(Long k) {
		write(GenderEntity.class,k);
	}
	
	@Override
	default Long getType() {
		return null;
	}
	
	@Override
	default void setType(Long k) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	default Long genderId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default Long getAreaId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default String getComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default void setGid(String k) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default String getGid() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	default void setBeginDateDay(Integer k) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default void setEndDateDay(Integer t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default void setTypeId(Long k) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default Long getTypeId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	default void setArea(Area<?> k) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default Area<?> getArea() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	default void setGenderId(Long k) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default void setComment(@NotNull String k) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default void setAreaId(Long k) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default void setBeginArea(Area<?> k) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default Area<?> getBeginArea() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	default void setEndArea(Area<?> t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default Area<?> getEndArea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default void setBeginYear(ArtistBeginDate<?> k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default ArtistBeginDate<?> getBeginYear() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default void setBeginMonth(ArtistBeginDate<?> k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default ArtistBeginDate<?> getBeginMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default void setBeginDay(ArtistBeginDate<?> k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default ArtistBeginDate<?> getBeginDay() {
		// TODO Auto-generated method stub
		return null;
	}
}
