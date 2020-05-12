package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.TrackBean;
import org.nanotek.beans.entity.Track;
import org.nanotek.entities.immutables.TrackIdEntity;
import org.nanotek.entities.immutables.TrackNameEntity;

public interface BaseTrackBean
<K extends BaseBean<K,Track<?>>> 
extends Base<K>,
BaseBean<K,Track<?>>,
MutableTrackIdEntity<Long>, 
MutableMediumEntity<BaseMediumBean<?>>,
MutableArtistCreditEntity<BaseArtistCreditBean<?>>,
MutableRecordingEntity<BaseRecordingBean<?>>,
MutableTrackPositionEntity<BaseTrackPositionBean<?>>,
MutableTrackNumberEntity<BaseTrackNumberBean<?>>,
MutableTrackLengthEntity<BaseTrackLengthBean<?>>,
MutableGidEntity<UUID>,
MutableTrackNameEntity<String>{

	@Override
	default Long getTrackId() {
		return read(TrackIdEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setTrackId(Long t) {
		write(MutableTrackIdEntity.class,t);
	}
	
	@Override
	default void setTrackName(String t) {
		write(MutableTrackNameEntity.class,t);
	}
	
	 @Override
	default String getTrackName() {
		return read(TrackNameEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	 
	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class, k);
	} 
	
	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(g->UUID.class.cast(g)).orElse(UUID.randomUUID());
	}
	
	default void setMediumId(Long id) {
		getMedium().setMediumId(id);
	}
	
	default Long getMediumId() { 
		return getMedium().getMediumId();
	}
	
	default void setArtistCreditId(Long id) { 
		getArtistCredit().setArtistCreditId(id);
	}
	
	default Long getArtistCreditId() { 
		return getArtistCredit().getArtistCreditId();
	}
	
	default void setRecordingId(Long id) { 
		getRecording().setRecordingId(id);
	}
	
	default Long getRecordingId() {
		return getRecording().getRecordingId();
	}
	
	default void setNumber(String s) { 
		getTrackNumber().setNumber(s);
	}
	
	default String getNumber() {
		return getTrackNumber().getNumber();
	}
	
	default void setPosition(Long position) {
		getTrackPosition().setPosition(position);
	}
	
	default Long getPosition() {
		return getTrackPosition().getPosition();
	}
	

	default void setLength(Long l) {
		getTrackLength().setLength(l);
	}
	
	default Long getLength() {
		return getTrackLength().getLength();
	}
	
	
	public static void main(String[] args) { 
		TrackBean<?> bean = new TrackBean<>();
		bean.setTrackId(100L);
		System.out.println(bean.getTrackId());
		bean.setTrackName("track name");
		System.out.println(bean.getTrackName());
		UUID uid = UUID.randomUUID();
		bean.setGid(uid);
		System.out.println(uid.toString());
		System.out.println(bean.getGid().toString());
		bean.setNumber("10");
		System.out.println(bean.getNumber());
		bean.setRecordingId(101l);
		System.out.println(bean.getRecordingId());
	}
}