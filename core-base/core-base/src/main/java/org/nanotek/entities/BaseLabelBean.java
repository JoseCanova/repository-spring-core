package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.entity.Label;
import org.nanotek.entities.immutables.LabelCodeEntity;
import org.nanotek.entities.immutables.LabelIdEntity;
import org.nanotek.entities.immutables.LabelNameEntity;

public interface BaseLabelBean
<K extends BaseBean<K,Label<?>>> 
extends 
Base<K>,
BaseBean<K,Label<?>>,
MutableLabelIdEntity<Long>,
MutableGidEntity<UUID>,
MutableLabelNameEntity<String>,
MutableLabelBeginDateEntity<BaseLabelBeginDateBean<?>>,
MutableLabelEndDateEntity<BaseLabelEndDateBean<?>>,
MutableLabelTypeEntity<BaseLabelTypeBean<?>>,
MutableAreaEntity<BaseAreaBean<?>>,
MutableLabelCodeEntity<Integer>{
	
	@Override
	default Long getLabelId() {
		return read(LabelIdEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setLabelId(Long id) {
		write(MutableLabelIdEntity.class,id);
	}
	
    @Override
    default UUID getGid() {
    	return read(GidEntity.class).map(m->UUID.class.cast(m)).orElse(null);
    }
    
    @Override
    default void setGid(UUID k) {
    	write(MutableGidEntity.class,k);
    }

    @Override
    default void setLabelName(String k) {
    	write(MutableLabelNameEntity.class,k);
    }
    
    @Override
    default String getLabelName() {
    	return read(LabelNameEntity.class).map(s->String.class.cast(s)).orElse("");
    }
    
	@Override
	default Integer getLabelCode() {
		return read(LabelCodeEntity.class).map(i->Integer.class.cast(i)).orElse(Integer.MIN_VALUE);
	}    
	
	@Override
	default void setLabelCode(Integer k) {
		write(MutableLabelCodeEntity.class,k);
	}
	
	default void setAreaId(Long id) { 
		getArea().setAreaId(id);
	}
	
	default Long getAreaId() { 
		return getArea().getAreaId();
	}
	
	default void setBeginYear(Integer y) { 
		getLabelBeginDate().setBeginYear(y);
	}
	
	default Integer getBeginYear() { 
		return getLabelBeginDate().getBeginYear();
	}
	
	default void setBeginMonth(Integer m) { 
		getLabelBeginDate().setBeginMonth(m);
	}
	
	default Integer getBeginMonth() { 
		return getLabelBeginDate().getBeginMonth();
	}
	
	default void setBeginDay(Integer d) { 
		getLabelBeginDate().setBeginDay(d);
	}
	
	default Integer getBeginDay() { 
		return getLabelBeginDate().getBeginDay();
	}
	
	default void setEndYear(Integer y) { 
		getLabelEndDate().setEndYear(y);
	}
	
	default Integer getEndYear() {
		return getLabelEndDate().getEndYear();
	}
	
	default void setEndMonth(Integer m) { 
		getLabelEndDate().setEndMonth(m);
	}
	
	default void setEndDay(Integer d) { 
		getLabelEndDate().setEndDay(d);
	}
	
	default Integer getEndDay() { 
		return getLabelEndDate().getEndDay();
	}
	
	
	default void setLabelTypeId(Long id) { 
		getLabelType().setTypeId(id);
	}
	
	default Long getLabelTypeId() { 
		return getLabelType().getTypeId();
	}
	
	
	
}
