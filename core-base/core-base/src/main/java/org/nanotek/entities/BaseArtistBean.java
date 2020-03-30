package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistBeginDate;
import org.nanotek.beans.entity.ArtistEndDate;
import org.nanotek.beans.entity.ArtistType;
import org.nanotek.beans.entity.Gender;

public interface BaseArtistBean
<K extends BaseBean<K,Artist<?>>> extends 
Base<K>,
BaseBean<K,Artist<?>>,
BaseAreaEntity<BaseAreaBean<?>>,
MutableArtistIdEntity<Long>,
MutableArtistSortNameEntity<BaseArtistSortNameBean<?>>,
MutableArtistCommentEntity<BaseArtistCommentBean<?>>,
MutableArtistBeginDateEntity<ArtistBeginDate<?>>,
MutableArtistEndDateEntity<ArtistEndDate<?>>,
MutableArtistTypeEntity<ArtistType<?>>,
MutableGenderEntity<Gender<?>>,
MutableAreaEntity<BaseAreaBean<?>>,
MutableArtistBeginAreaEntity<BaseAreaBean<?>>,
MutableArtistEndAreaEntity<BaseAreaBean<?>>,
MutableGidEntity<UUID>,MutableNameEntity<String>
{

	



}
