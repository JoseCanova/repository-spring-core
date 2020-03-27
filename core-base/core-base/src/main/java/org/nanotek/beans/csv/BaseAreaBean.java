package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.IdBase;
import org.nanotek.ImmutableBase;
import org.nanotek.entities.MutableAreaIdEntity;
import org.nanotek.entities.MutableBeginDayEntity;
import org.nanotek.entities.MutableBeginMonthEntity;
import org.nanotek.entities.MutableBeginYearEntity;
import org.nanotek.entities.MutableCommentEntity;
import org.nanotek.entities.MutableEndDayEntity;
import org.nanotek.entities.MutableEndMonthEntity;
import org.nanotek.entities.MutableEndYearEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableNameEntity;
import org.nanotek.entities.MutableTypeEntity;

public interface BaseAreaBean<K extends ImmutableBase<K,ID> , ID extends IdBase<?,?>> extends 
BaseBean<K,ID>,
MutableAreaIdEntity<Long>,
MutableGidEntity<String>,
MutableNameEntity<String>,
MutableBeginYearEntity<Integer>,
MutableBeginMonthEntity<Integer>,
MutableBeginDayEntity<Integer>,
MutableEndYearEntity<Integer>,
MutableEndMonthEntity<Integer>,
MutableEndDayEntity<Integer>,
MutableCommentEntity<String>,
MutableTypeEntity<Long> {

	
}

