package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseGroupComment;
import org.nanotek.entities.immutables.CommentEntity;

public interface BaseReleaseGroupCommentBean
<K extends BaseBean<K,ReleaseGroupComment<?>>> 
extends
Base<K>,
BaseBean<K,ReleaseGroupComment<?>>,
BaseAreaCommentEntity<ReleaseGroupComment<?>>, 
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