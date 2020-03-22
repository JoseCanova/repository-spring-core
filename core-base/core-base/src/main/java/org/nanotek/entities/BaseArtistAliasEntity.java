package org.nanotek.entities;

import org.nanotek.BaseEntity;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistAlias;
import org.nanotek.beans.entity.ArtistAliasBeginDate;
import org.nanotek.beans.entity.ArtistAliasEndDate;
import org.nanotek.beans.entity.ArtistAliasLocale;
import org.nanotek.beans.entity.ArtistAliasSortName;
import org.nanotek.beans.entity.ArtistAliasType;

public interface BaseArtistAliasEntity<K> 
extends 
MutableAliasIdEntity<Long>,
MutableArtistAliasSortNameEntity<ArtistAliasSortName<?>>,
MutableArtistEntity<Artist<?>> , 
MutableArtistAliasTypeEntity<ArtistAliasType<?,?>> , 
MutableArtistAliasLocaleEntity<ArtistAliasLocale<?>>,
MutableArtistAliasBeginDateEntity<ArtistAliasBeginDate>,
MutableArtistAliasEndDateEntity<ArtistAliasEndDate>{
	
}
