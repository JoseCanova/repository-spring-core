package org.nanotek.proxy.entities;

import org.nanotek.beans.entity.Area;
import org.nanotek.entities.BaseAreaEntity;
import org.nanotek.entities.MutableBeginDateEntity;
import org.nanotek.entities.MutableCommentEntity;
import org.nanotek.entities.MutableEndDateEntity;

public interface BaseAreaEntityProxy extends 
BaseAreaEntity<Area<?>> , 
MutableBeginDateEntity<Integer>,
MutableEndDateEntity<Integer>,
MutableCommentEntity<String>{

	@Override
	default void setBeginYear(Integer k) {
		getAreaBeginDate().setBeginYear(k);
	}
	
	@Override
	default void setBeginMonth(Integer k) {
		getAreaBeginDate().setBeginMonth(k);
	}
	
	@Override
	default void setBeginDay(Integer k) {
		getAreaBeginDate().setBeginDay(k);
	}
	
	@Override
	default Integer getBeginDay() {
		return getAreaBeginDate().getBeginDay();
	}
	
	@Override
	default Integer getBeginYear() {
		return getAreaBeginDate().getBeginYear();
	}
	
	@Override
	default Integer getBeginMonth() {
		return getAreaBeginDate().getBeginMonth();
	}
	
	
}
