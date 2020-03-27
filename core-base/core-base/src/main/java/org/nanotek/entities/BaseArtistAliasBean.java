package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistAlias;


public interface BaseArtistAliasBean<K extends BaseBean<K,ArtistAlias<?>>>
extends BaseBean<K,ArtistAlias<?>>,
Base<K>,
MutableAliasIdEntity<Long>,
MutableArtistAliasSortNameEntity<String>,
MutableArtistIdEntity<Long> , 
MutableArtistAliasTypeEntity<Long> , 
MutableArtistAliasLocaleEntity<Long>,
MutableNameEntity<String>,
MutableBeginYearEntity<Integer>,
MutableBeginMonthEntity<Integer>,
MutableBeginDayEntity<Integer>{

}
