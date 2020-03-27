package org.nanotek.beans.csv;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Area;
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


//public interface BaseAreaTypeBean<K extends BaseBean<K,AreaType<?>>> 
//extends Base<K>,
//BaseBean<K,AreaType<?>>
//<K extends BaseBean<K,AreaType<?>>,ID extends AreaType<?>> 
//extends CsvBaseBean<ID>
//implements BaseAreaTypeBean<K>


public interface BaseAreaBean<K extends BaseBean<K,Area<?>>> extends 
Base<K>,
BaseBean<K,Area<?>>,
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

	@Override
	default void setAreaId(Long k) {
		write(MutableAreaIdEntity.class,k);
	}
	
	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}
	
	@Override
	default void setBeginYear(Integer k) {
		write(MutableBeginYearEntity.class,k);
	}
}

