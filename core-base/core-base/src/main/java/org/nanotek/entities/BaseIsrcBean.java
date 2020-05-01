package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Isrc;
import org.nanotek.entities.immutables.IsrcEntity;
import org.nanotek.entities.immutables.IsrcIdEntity;
import org.nanotek.entities.immutables.IsrcSourceEntity;

public interface BaseIsrcBean
<K extends BaseBean<K,Isrc<?>>> 
extends 
Base<K>,
BaseBean<K,Isrc<?>>,
MutableIsrcIdEntity<Long>,
MutableRecordingEntity<BaseRecordingBean<?>>,
MutableIsrcEntity<String>,
MutableIsrcSourceEntity<Integer>{

	@Override
	default Long getIsrcId() {
		return read(IsrcIdEntity.class).map(i->Long.class.cast(i)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setIsrcId(Long k) {
		write(MutableIsrcIdEntity.class,k);
	}
	
	@Override
	default String getIsrc() {
		return read(IsrcEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setIsrc(String k) {
		write(MutableIsrcEntity.class,k);
	}
	
	@Override
	default Integer getIsrcSource() {
		return read(IsrcSourceEntity.class).map(i->Integer.class.cast(i)).orElse(Integer.MIN_VALUE);
	}
	
	@Override
	default void setIsrcSource(Integer k) {
		write(MutableIsrcSourceEntity.class,k);
	}
	
	default void setRecordingId(Long recordingId){ 
		getRecording().setRecordingId(recordingId);
	}
	
	default Long getRecordingid() { 
		return getRecording().getRecordingId();
	}
	
}
