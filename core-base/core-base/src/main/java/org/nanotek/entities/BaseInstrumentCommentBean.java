package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.InstrumentComment;
import org.nanotek.entities.immutables.CommentEntity;

public interface BaseInstrumentCommentBean<K extends BaseBean<K,InstrumentComment<?>>> 
extends
Base<K>,
BaseBean<K,InstrumentComment<?>>,
BaseAreaCommentEntity<InstrumentComment<?>>, 
MutableCommentEntity<String>{
	
	@Override
	default void setComment(String k) {
		write(MutableCommentEntity.class,k);
	}
	
	@Override
	default String getComment() {
		return read(CommentEntity.class).map(c ->String.class.cast(c)).orElse("");
	}

}
