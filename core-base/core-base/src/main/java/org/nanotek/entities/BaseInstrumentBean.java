package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.InstrumentBean;
import org.nanotek.beans.entity.Instrument;
import org.nanotek.beans.entity.InstrumentType;
import org.nanotek.entities.immutables.InstrumentIdEntity;
import org.nanotek.entities.immutables.InstrumentNameEntity;

public interface BaseInstrumentBean<K extends BaseBean<K,Instrument<?>>> 
extends Base<K>,
BaseBean<K,Instrument<?>>
,MutableInstrumentNameEntity<String>
,MutableGidEntity<UUID>,
MutableInstrumentIdEntity<Long>,
MutableInstrumentTypeEntity<BaseInstrumentTypeBean<?>>,
MutableInstrumentCommentEntity<BaseInstrumentCommentBean<?>>,
MutableInstrumentDescriptionEntity<BaseInstrumentDescriptionBean<?>>{
	
    @Override
    default UUID getGid() {
    	return read(GidEntity.class).map(m->UUID.class.cast(m)).orElse(null);
    }
    
    @Override
    default void setGid(UUID k) {
    	write(MutableGidEntity.class,k);
    }
	
	@Override
	default void setInstrumentName(String k) {
		write(MutableInstrumentNameEntity.class,k);
	}
	
	@Override	
	default String getInstrumentName() {
		return read(InstrumentNameEntity.class).filter(v-> v!=null).map(v -> String.class.cast(v)).orElse(new String());
	}
	
	@Override
	default Long getInstrumentId() {
		return read(InstrumentIdEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setInstrumentId(Long t) {
		write(MutableInstrumentIdEntity.class,t);
	}
	
	default void setTypeId(Long id) { 
		getInstrumentType().setTypeId(id);
	}
	
	default Long getTypeId() {
		return getInstrumentType().getTypeId();
	}
	
	default String getComment() { 
		return getInstrumentComment().getComment();
	}
	
	default void setComment(String comment) { 
		getInstrumentComment().setComment(comment);
	}
	
	default String getDescription() { 
		return getInstrumentDescription().getDescription();
	}
	
	default void setDescription(String description) { 
		getInstrumentDescription().setDescription(description);
	}
	
	public static void main(String[] args) {
		InstrumentBean<?> bean = new InstrumentBean<>();
		bean.setInstrumentName("theInstrumentName");
		System.out.println(bean.getInstrumentName());
		bean.setComment("this is a comment");
		System.out.println(bean.getComment());
		bean.setDescription("this is a description");
		System.out.println(bean.getDescription());
	}
}
