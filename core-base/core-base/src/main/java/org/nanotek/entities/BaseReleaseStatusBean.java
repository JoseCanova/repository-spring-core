package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.ReleaseStatusBean;
import org.nanotek.beans.entity.ReleaseStatus;
import org.nanotek.entities.immutables.ReleaseStatusIdEntity;
import org.nanotek.entities.immutables.ReleaseStatusNameEntity;

public interface BaseReleaseStatusBean
<K extends BaseBean<K,ReleaseStatus<?>>> 
extends Base<K>,
BaseBean<K,ReleaseStatus<?>>,
MutableReleaseStatusIdEntity<Long>,
MutableGidEntity<UUID>,
MutableReleaseStatusNameEntity<String>
{

	@Override
	default void setReleaseStatusId(Long t) {
		write(MutableReleaseStatusIdEntity.class,t);
	}
	
	@Override
	default Long getReleaseStatusId() {
		return read(ReleaseStatusIdEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
	
	
	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}
	
	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(u->UUID.class.cast(u)).orElse(UUID.randomUUID());
	}
	
	@Override
	default void setReleaseStatusName(String k) {
		write(MutableReleaseStatusNameEntity.class,k);
	}
	
	@Override
	default String getReleaseStatusName() {
		return read(ReleaseStatusNameEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	

	public static void main(String[] args) { 
		ReleaseStatusBean<?> bean = new ReleaseStatusBean<>();
		bean.setReleaseStatusId(123L);
		System.out.println(bean.getReleaseStatusId());
		bean.setReleaseStatusName("name");
		System.out.println(bean.getReleaseStatusName());
		bean.setGid(UUID.randomUUID());
		System.out.println(bean.getGid().toString());
	}
}
