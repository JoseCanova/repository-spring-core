package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.entities.immutables.CommentEntity;

public interface BaseAreaCommentBean
<K extends BaseBean<K,AreaComment<?>>> 
extends
Base<K>,
BaseBean<K,AreaComment<?>>,
BaseAreaCommentEntity<AreaComment<?>>, 
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
