package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistAliasBeginDateBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistAlias;
import org.nanotek.beans.entity.ArtistAliasLocale;
import org.nanotek.beans.entity.ArtistAliasSortName;
import org.nanotek.beans.entity.ArtistAliasType;
import org.nanotek.opencsv.ArtistAliasEndDateBean;


public interface BaseArtistAliasBean
<K extends BaseBean<K,ArtistAlias<?>>>
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
MutableBeginDayEntity<Integer>,
MutableArtistAliasSortNameEntity<ArtistAliasSortName<?>>,
MutableArtistEntity<Artist<?>> , 
MutableArtistAliasTypeEntity<ArtistAliasType<?>> , 
MutableArtistAliasLocaleEntity<ArtistAliasLocale<?>>,
MutableArtistAliasBeginDateEntity<BaseArtistAliasBeginDateBean<?>>,
MutableArtistAliasEndDateEntity<BaseArtistAliasEndDateBean<?>>
{

}
