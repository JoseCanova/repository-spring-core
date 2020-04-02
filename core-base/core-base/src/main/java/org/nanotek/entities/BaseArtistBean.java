package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.entities.immutables.ArtistIdEntity;

public interface BaseArtistBean
<K extends BaseBean<K,Artist<?>>> extends 
Base<K>,
BaseBean<K,Artist<?>>,
MutableArtistAreaEntity<BaseAreaBean<?>>,
MutableArtistIdEntity<Long>,
MutableArtistSortNameEntity<BaseArtistSortNameBean<?>>,
MutableArtistCommentEntity<BaseArtistCommentBean<?>>,
MutableArtistBeginDateEntity<BaseArtistBeginDateBean<?>>,
MutableArtistEndDateEntity<BaseArtistEndDateBean<?>>,
MutableArtistTypeEntity<BaseArtistTypeBean<?>>,
MutableGenderEntity<BaseGenderBean<?>>,
MutableAreaEntity<BaseAreaBean<?>>,
MutableArtistBeginAreaEntity<BaseAreaBean<?>>,
MutableArtistEndAreaEntity<BaseAreaBean<?>>,
MutableGidEntity<UUID>,MutableNameEntity<String>
{
	/**
	 * artistId: 0
    gid: 1
    name: 2
    sortName: 3
    beginDateYear: 4
    beginDateMonth: 5
    beginDateDay: 6
    endDateYear: 7
    endDateMonth: 8
    endDateDay: 9
    typeId: 10
    areaId: 11
    genderId: 12 
    comment: 13
    editsPending: 14
    lastUpdated: 15
    ended: 16
    beginAreaId: 17
    endAreaId: 18
	 */
	
	
	
	@Override
	default String getName() {
		return read(NameEntity.class).map(n->String.class.cast(n)).orElse("");
	}
	
	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}
	
	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(v->UUID.class.cast(v)).orElse(UUID.randomUUID());
	}
	
	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}
	
	@Override
	default Long getArtistId() {
		return read(ArtistIdEntity.class).map(v->Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setArtistId(Long k) {
		write(MutableArtistIdEntity.class,k);
	}
	
	default void setSortName(String sortName) {
		getArtistSortName().setSortName(sortName);
	}
	
	default String getSortName() {
		return getArtistSortName().getSortName();
	}
	
	default void setBeginDateYear(Integer y) {
		getArtistBeginDate().setBeginYear(y);
	}
	
	default Integer getBeginYear() {
		return getArtistBeginDate().getBeginYear();
	}
	
	default void setBeginMonth(Integer m) {
		getArtistBeginDate().setBeginMonth(m);
	}
	
	default Integer getBeginMonth() {
		return getArtistBeginDate().getBeginMonth();
	}
	
	default void setBeginDay(Integer k) {
		getArtistBeginDate().setBeginDay(k);
	}
	
	default Integer getBeginDay() {
		return getArtistBeginDate().getBeginDay();
	}
	
	default void setEndDateYear(Integer y) {
		getArtistBeginDate().setBeginYear(y);
	}
	
	default Integer getEndYear() {
		return getArtistBeginDate().getBeginYear();
	}
	
	default void setEndMonth(Integer m) {
		getArtistEndDate().setEndMonth(m);
	}
	
	default Integer getEndMonth() {
		return getArtistEndDate().getEndMonth();
	}
	
	default Integer getEndDay() {
		return getArtistEndDate().getEndDay();
	}
	
	default void setEndDay(Integer v) {
		 getArtistEndDate().setEndDay(v);
	}
	
	default void setTypeId(Long id) {
		getArtistType().setTypeId(id);
	}
	
	default Long getTypeId() {
		return getArtistType().getTypeId();
	}
	
	default void setAreaId(Long k) {
		getArea().setAreaId(k);
	}
	
	default Long getAreaId() {
		return getArea().getAreaId();
	}
	
	default void setBeginAreaId(Long k) {
		getBeginArea().setAreaId(k);
	}
	
	default Long getBeginAreaId() {
		return getBeginArea().getAreaId();
	}
	
	default void setGenderId(Long id) {
		getGender().setTypeId(id);
	}
	
	default Long getGenderId() {
		return getGender().getTypeId();
	}
	
	default void setComment(String comment) {
		getArtistComment().setComment(comment);
	}
	
	default String  getComment() {
		return getArtistComment().getComment();
	}
	
	default void setEndAreaId(Long  id) {
		getEndArea().setAreaId(id);
	}
	
	default Long getEndAreaId() {
		return getEndArea().getAreaId();
	}
	
	
	public static void main (String[] args) {
		ArtistBean artistBean = new ArtistBean();
		System.out.println(artistBean.toString());
		
	}
	
}
