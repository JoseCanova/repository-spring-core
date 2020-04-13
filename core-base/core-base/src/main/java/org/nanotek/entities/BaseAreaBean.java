package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.GidEntity;
import org.nanotek.Id;
import org.nanotek.beans.csv.AreaBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.entities.immutables.AreaIdEntity;
import org.nanotek.entities.immutables.AreaNameEntity;


public interface BaseAreaBean
<K extends BaseBean<K,Area<?>>> 
extends 
Base<K>,
BaseBean<K,Area<?>>,
MutableAreaIdEntity<Long>,
MutableAreaNameEntity<String>,
MutableBeginYearEntity<Integer>,
MutableBeginMonthEntity<Integer>,
MutableBeginDayEntity<Integer>,
MutableEndYearEntity<Integer>,
MutableEndMonthEntity<Integer>,
MutableEndDayEntity<Integer>,
MutableCommentEntity<String>,
MutableTypeIdEntity<Long>,
MutableTypeEntity<BaseAreaTypeBean<?>>,
MutableAreaCommentEntity<BaseAreaCommentBean<?>>,
MutableAreaBeginDateEntity<BaseAreaBeginDateBean<?>>,
MutableAreaEndDateEntity<BaseAreaEndDateBean<?>>,
MutableGidEntity<UUID>{

	@Override
	default void setAreaId(Long k) {
		write(MutableAreaIdEntity.class,k);
	}
	
	@Override
	default Long getAreaId() {
		return read(AreaIdEntity.class).filter(v-> v!=null).map(v -> Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setAreaName(String k) {
		write(MutableAreaNameEntity.class,k);
	}
	
	@Override	
	default String getAreaName() {
		return read(AreaNameEntity.class).filter(v-> v!=null).map(v -> String.class.cast(v)).orElse(new String());
	}

	@Override
	default void setBeginYear(Integer k) {
		getAreaBeginDate().setBeginYear(k);
	}
	
	@Override
	default Integer getBeginYear() {
		return getAreaBeginDate().getBeginYear();
	}

	@Override
	default void setBeginMonth(Integer k) {
		getAreaBeginDate().setBeginMonth(k);
	}
	
	@Override
	default Integer getBeginMonth() {
		return getAreaBeginDate().getBeginMonth();
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
	default void setEndYear(Integer t) {
		getAreaEndDate().setEndYear(t);
	}
	
	@Override
	default Integer getEndYear() {
		return getAreaEndDate().getEndYear();
	}
	
	@Override
	default void setEndMonth(Integer t) {
		getAreaEndDate().setEndMonth(t);
	}

	@Override
	default Integer getEndMonth() {
		return getAreaEndDate().getEndMonth();
	}

	@Override
	default void setEndDay(Integer t) {
		getAreaEndDate().setEndYear(t);
	}
	
	@Override
	default Integer getEndDay() {
		return getAreaEndDate().getEndDay();
	}

	@Override
	default void setComment(String k) {
		getAreaComment().setComment(k);
	}
	
	@Override
	default String getComment() { 
		return getAreaComment().getComment();
	}
	
	@Override
	BaseAreaBeginDateBean<?> getAreaBeginDate();

    @Override
    void setAreaBeginDate(BaseAreaBeginDateBean<?> k);
    
    @Override
    BaseAreaEndDateBean<?> getAreaEndDate();
    
    @Override
    void setAreaEndDate(BaseAreaEndDateBean<?> k);
    
    @Override
    default void setGid(UUID k) {
    	write(MutableGidEntity.class,k);
    }

    @Override
    default Long getTypeId() {
    	return getType().getTypeId();
    }
    
    @Override
    default void setTypeId(Long k) {
    	getType().setTypeId(k);
    }
    
    @Override
    void setAreaComment(BaseAreaCommentBean<?> k);
    
    @Override
    void setType(BaseAreaTypeBean<?> k);
    
    @Override
    BaseAreaTypeBean<?> getType();
    
    @Override
    public BaseAreaCommentBean<?> getAreaComment();
    
    @Override
    default UUID getGid() {
    	return read(GidEntity.class).map(m->UUID.class.cast(m)).orElse(null);
    }
    
    @Override
    default Area<?> getId() {
    	return read(Id.class).map(m->Area.class.cast(m)).orElseThrow(BaseException::new);
    }
    
    
    public static void main (String[] args) {
    	AreaBean bean = new AreaBean();
    	bean.setAreaId(1000l);
    	bean.setAreaName("this is bame");
    	System.out.println(bean.getAreaName());
    	bean.setGid(UUID.randomUUID());
    	bean.setComment("this is a comment");
    	System.out.println(bean.getComment());
    	bean.setAreaId(1000L);
    	bean.setBeginDay(10);
    	bean.setBeginMonth(10);
    	bean.setBeginYear(10);
    	bean.setEndYear(10);
    	bean.setEndMonth(10);
    	bean.setEndDay(10);
    	bean.getAreaId();
    	bean.getComment();
    	bean.getTypeId();
    	
    	
    	
    }
    
}