package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.RecordingBean;
import org.nanotek.beans.entity.Recording;
import org.nanotek.beans.entity.Track;
import org.nanotek.entities.immutables.RecordingIdEntity;
import org.nanotek.entities.immutables.RecordingNameEntity;

public interface BaseRecordingBean
<K extends BaseBean<K,Recording<?>>> 
extends Base<K>,
BaseBean<K,Recording<?>>,
MutableRecordingIdEntity<Long> , 
MutableArtistCreditEntity<BaseArtistCreditBean<?>>,
MutableTrackEntitySet<Track<?>>,
MutableRecordingLengthEntity<BaseRecordingLengthBean<?>>,
MutableGidEntity<UUID>,
MutableRecordingNameEntity<String>{

	@Override
	default Long getRecordingId() {
		return read(RecordingIdEntity.class).map(m->Long.class.cast(m)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setRecordingId(Long k) {
		write(MutableRecordingIdEntity.class,k);
	}
	
	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(g->UUID.class.cast(g)).orElse(UUID.randomUUID());
	}
	
	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}
	
	default String getRecordingName() {
		return read(RecordingNameEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	default void setRecordingName(String k) {
		write(MutableRecordingNameEntity.class,k);
	}
	
	default void setLength(Long l) {
		getRecordingLength().setLength(l);
	}
	
	default Long getLength() {
		return getRecordingLength().getLength();
	}
	
	
	public static void main(String[] args) { 
		RecordingBean<?> bean = new RecordingBean<>();
		bean.setRecordingId(1000L);
		bean.setLength(1002L);
		UUID id = UUID.randomUUID();
		System.out.println(id.toString());
		bean.setGid(id);
		bean.setRecordingName("TheName");
		System.out.println(bean.getRecordingId());
		System.out.println(bean.getGid().toString());
		System.out.println(bean.getRecordingName());
		System.out.println(bean.getLength());
	}
}
