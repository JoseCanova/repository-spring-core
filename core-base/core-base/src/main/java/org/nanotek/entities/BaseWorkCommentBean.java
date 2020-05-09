package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.WorkComment;
import org.nanotek.entities.immutables.CommentEntity;

public interface BaseWorkCommentBean
<K extends BaseBean<K,WorkComment<?>>> 
extends
Base<K>,
BaseBean<K,WorkComment<?>>,
BaseAreaCommentEntity<WorkComment<?>>, 
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
