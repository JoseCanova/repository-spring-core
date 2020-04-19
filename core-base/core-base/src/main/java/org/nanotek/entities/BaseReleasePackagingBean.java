package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.ReleasePackagingBean;
import org.nanotek.beans.entity.ReleasePackaging;
import org.nanotek.entities.immutables.ReleasePackagingNameEntity;
import org.nanotek.entities.immutables.ReleasePackagingIdEntity;

public interface BaseReleasePackagingBean
<K extends BaseBean<K,ReleasePackaging<?>>> 
extends Base<K>,
BaseBean<K,ReleasePackaging<?>>,
MutableReleasePackagingIdEntity<Long>,
MutableGidEntity<UUID>,
MutableReleasePackagingNameEntity<String>{
	

	@Override
	default Long getReleasePackagingId() {
		return read(ReleasePackagingIdEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setReleasePackagingId(Long k) {
		write(MutableReleasePackagingIdEntity.class,k);
	}
	
	@Override
	default void setReleasePackagingName(String name) {
		write(MutableReleasePackagingNameEntity.class,name);
	}
	
	@Override
	default String getReleasePackagingName() {
		return read(ReleasePackagingNameEntity.class).map(n->String.class.cast(n)).orElse("");
	}
	
	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}
	
	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(g->UUID.class.cast(g)).orElse(UUID.randomUUID());
	}
	
	public static void main(String[] args) { 
		ReleasePackagingBean<?> bean = new ReleasePackagingBean<>();
	}
}
