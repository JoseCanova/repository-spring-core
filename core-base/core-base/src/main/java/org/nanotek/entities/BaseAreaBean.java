package org.nanotek.entities;

import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.AreaEndDate;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.entities.immutables.AreaCommentEntity;
import org.nanotek.entities.immutables.AreaEndDateEntity;
import org.nanotek.entities.immutables.AreaIdEntity;
import org.nanotek.entities.immutables.BeginYearEntity;
import org.nanotek.entities.immutables.CommentEntity;
import org.nanotek.entities.immutables.EndDateDayEntity;
import org.nanotek.entities.immutables.EndMonthEntity;
import org.nanotek.entities.immutables.EndYearEntity;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

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
MutableAreaCommentEntity<AreaComment<?>>,
MutableAreaBeginDateEntity<AreaBeginDate<?>>,
MutableAreaEndDateEntity<AreaEndDate<?>>,
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
		write(AreaBeginDateEntity.class,  MutableBeginYearEntity.class,k);
	}
	
	@Override
	default Integer getBeginYear() {
		return read(BeginYearEntity.class).filter(v-> v!=null).map(v -> Integer.class.cast(v)).orElse(Integer.MIN_VALUE);
	}

	@Override
	default void setBeginMonth(Integer k) {
		write(MutableBeginMonthEntity.class , k);
	}
	
	@Override
	default Integer getBeginMonth() {
		return read(BeginMonthEntity.class).filter(v-> v!=null).map(v->Integer.class.cast(v)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setBeginDay(Integer k) {
		write(MutableBeginDayEntity.class,k);
	}
	
	@Override
	default Integer getBeginDay() {
		return read(BeginDayEntity.class).map(v ->Integer.class.cast(v)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setEndYear(Integer t) {
		write(EndYearEntity.class,t);
	}
	
	@Override
	default Integer getEndYear() {
		return read(EndYearEntity.class).map(v->Integer.class.cast(v)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setEndMonth(Integer t) {
		write(MutableEndMonthEntity.class,t);
	}

	@Override
	default Integer getEndMonth() {
		return read(EndMonthEntity.class).map(v ->Integer.class.cast(v)).orElse(Integer.MIN_VALUE);
	}

	@Override
	default void setEndDay(Integer t) {
		write(MutableEndDayEntity.class,t);
	}
	
	@Override
	default Integer getEndDay() {
		return read(EndDateDayEntity.class).map(v->Integer.class.cast(v)).orElse(Integer.MIN_VALUE);
	}

	@Override
	default void setComment(@NotNull String k) {
		write(Area.class , MutableAreaCommentEntity.class,new AreaComment<>(k));
	}
	
	@Override
	default String getComment() {
		Optional<CommentEntity<String>> ce = read(AreaCommentEntity.class).map(v -> AreaComment.class.cast(v)).map(m->CommentEntity.class.cast(m));
		return ce.get().getComment();
	}
	
	@Override
	default AreaBeginDate<?> getAreaBeginDate() {
		return read(MutableAreaBeginDateEntity.class).map(m->AreaBeginDate.class.cast(m)).orElse(new AreaBeginDate<>());
	}

    @Override
    default void setAreaBeginDate(AreaBeginDate<?> k) {
    	write(MutableAreaBeginDateEntity.class,k);
    }
    
    @Override
    default AreaEndDate<?> getAreaEndDate() {
    	return read(AreaEndDateEntity.class).map(d-> AreaEndDate.class.cast(d)).orElse(new AreaEndDate<>());
    }
    
    @Override
    default void setAreaEndDate(AreaEndDate<?> k) {
    	write(MutableAreaEndDateEntity.class,k);
    }
    
    //TODO, implement method.
    @Override
    default void setGid(UUID k) {
    }

    @Override
    default Long getTypeId() {
    	return read(MutableTypeIdEntity.class).map(v -> Long.class.cast(v)).orElse(Long.MIN_VALUE);
    }
    
    @Override
    default void setTypeId(Long k) {
    	write(MutableTypeIdEntity.class,k);
    }
    
    @Override
    default void setAreaComment(AreaComment<?> k) {
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
    default AreaComment<?> getAreaComment() {
    	return read(MutableAreaCommentEntity.class).map(m -> AreaComment.class.cast(m)).orElse(new AreaComment<>());
    }
    
    @Override
    default UUID getGid() {
    	return read(MutableGidEntity.class).map(m->UUID.class.cast(m)).orElse(null);
    }
    
    @Override
    default Area<?> getId() {
    	return read(Id.class).map(m->Area.class.cast(m)).orElseThrow(BaseException::new);
    }
    
}