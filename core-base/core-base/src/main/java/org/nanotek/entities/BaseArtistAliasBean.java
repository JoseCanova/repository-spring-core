package org.nanotek.entities;

import org.nanotek.BaseBean;
import org.nanotek.IdBase;
import org.nanotek.ImmutableBase;

public interface BaseArtistAliasBean<K extends ImmutableBase<K,ID> , ID extends IdBase<?,?>> 
extends BaseBean<K,ID>,
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
