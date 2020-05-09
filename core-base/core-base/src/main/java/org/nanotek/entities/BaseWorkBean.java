package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.WorkBean;
import org.nanotek.beans.entity.Work;
import org.nanotek.entities.immutables.WorkNameEntity;

public interface BaseWorkBean
<K extends BaseBean<K,Work<?>>> 
extends Base<K>,
BaseBean<K,Work<?>>,
MutableWorkIdEntity<Long>,
MutableGidEntity<UUID>,
MutableWorkNameEntity<String>,
MutableWorkTypeEntity<BaseWorkTypeBean<?>>,
MutableWorkCommentEntity<BaseWorkCommentBean<?>>
{

	@Override
	default Long getWorkId() {
		return read(WorkIdEntity.class).map(w ->Long.class.cast(w)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setWorkId(Long workId) {
		write(MutableWorkIdEntity.class,workId);
	}
	
	@Override
	default String getWorkName() {
		return read(WorkNameEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setWorkName(String k) {
		write(MutableWorkNameEntity.class,k);
	}
	
	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(u->UUID.class.cast(u)).orElse(UUID.randomUUID());
	}
	
	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}
	
	default void setTypeId(Long id) { 
		Long theId = id == null? 28l : id;
		getWorkType().setTypeId(theId);
	}
	
	default Long getTypeId() { 
		return getWorkType().getTypeId();
	}
	
	default String getComment() { 
		return getWorkComment().getComment();
	}
	
	default void setComment(String comment) { 
		getWorkComment().setComment(comment);
	}
	
	public static void main(String[] args) { 
		WorkBean<?> bean = new WorkBean<>();
		bean.setComment("this is a comment");
		bean.setWorkName("this is a work name");
		bean.setWorkId(100l);
		bean.setTypeId(1001l);
		System.out.println(bean.getWorkId());
		System.out.println(bean.getWorkName());
		System.out.println(bean.getComment());
		System.out.println(bean.getTypeId());
	}
	
}
