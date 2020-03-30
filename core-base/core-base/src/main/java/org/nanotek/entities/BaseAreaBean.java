package org.nanotek.entities;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.GidEntity;
import org.nanotek.Id;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.entities.immutables.AreaIdEntity;


public interface BaseAreaBean<K extends BaseBean<K,Area<?>>> 
extends 
Base<K>,
BaseBean<K,Area<?>>,
MutableAreaIdEntity<Long>,
MutableNameEntity<String>,
MutableBeginYearEntity<Integer>,
MutableBeginMonthEntity<Integer>,
MutableBeginDayEntity<Integer>,
MutableEndYearEntity<Integer>,
MutableEndMonthEntity<Integer>,
MutableEndDayEntity<Integer>,
MutableCommentEntity<String>,
MutableTypeIdEntity<Long>,
MutableTypeEntity<AreaType<?>>,
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
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}
	
	@Override	
	default String getName() {
		return read(NameEntity.class).filter(v-> v!=null).map(v -> String.class.cast(v)).orElse(new String());
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
	default void setComment(@NotNull String k) {
		write(Area.class , MutableAreaCommentEntity.class,new AreaComment<>(k));
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
    	return read(TypeIdEntity.class).map(v -> Long.class.cast(v)).orElse(Long.MIN_VALUE);
    }
    
    @Override
    default void setTypeId(Long k) {
    	write(MutableTypeIdEntity.class,k);
    }
    
    @Override
    default void setAreaComment(BaseAreaCommentBean<?> k) {
    	write(MutableAreaCommentEntity.class,k);
    }
    
    @Override
    default void setType(AreaType<?> k) {
    	write(MutableTypeEntity.class,k);
    }
    
    @Override
    default AreaType<?> getType() {
    	return read(MutableTypeEntity.class).map(m -> AreaType.class.cast(m)).orElse(new AreaType<>());
    }
    
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
    
}