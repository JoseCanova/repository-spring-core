package org.nanotek.entities;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistBeginDate;
import org.nanotek.beans.entity.ArtistEndDate;
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
BaseAreaEntity<Area<?>>,
MutableArtistIdEntity<Long>,
MutableGidEntity<String>,
MutableNameEntity<String>,
MutableArtistBeginDateEntity<ArtistBeginDate<?>>,
MutableBeginDateEntity<ArtistBeginDate<?>>,
MutableBeginDateYearEntity<Integer>,
MutableBeginDateMonthEntity<Integer>,
MutableBeginDateDayEntity<Integer>,
MutableArtistEndDateEntity<ArtistEndDate<?>>,
MutableEndDateYearEntity<Integer>,
MutableEndDateMonthEntity<Integer>,
MutableEndDateDayEntity<Integer>,
MutableTypeIdEntity<Long>,
MutableAreaEntity<Area<?>>,
MutableGenderEntity<Long>,
MutableGenderIdEntity<Long>,
MutableCommentEntity<String>,
MutableAreaIdEntity<Long>,
MutableArtistBeginAreaEntity<Area<?>>,
MutableArtistEndAreaEntity<Area<?>>,
MutableTypeEntity<Long>
//,
//MutableBeginDateEntity<Integer>

{

	



}
