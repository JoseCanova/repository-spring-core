package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.RecordingBean;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.Recording;
import org.nanotek.beans.entity.RecordingLength;
import org.nanotek.beans.entity.Track;
import org.nanotek.entities.immutables.RecordingEntity;
import org.nanotek.entities.immutables.RecordingIdEntity;

public interface BaseRecordingBean
<K extends BaseBean<K,Recording<?>>> 
extends Base<K>,
BaseBean<K,Recording<?>>,
MutableRecordingIdEntity<Long> , 
MutableArtistCreditEntity<BaseArtistCreditBean<?>>,
MutableTrackEntitySet<Track<?>>,
MutableRecordingLengthEntity<RecordingLength<?>>,
MutableGidEntity<UUID>,MutableNameEntity<String>{

	@Override
	default Long getRecordingId() {
		return read(RecordingIdEntity.class).map(m->Long.class.cast(m)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setRecordingId(Long k) {
		write(RecordingEntity.class,k);
	}
	
	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(g->UUID.class.cast(g)).orElse(UUID.randomUUID());
	}
	
	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}
	
	default String getName() {
		return read(NameEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}
	
	
	public static void main(String[] args) { 
		RecordingBean<?> bean = new RecordingBean<>();
	}
}
