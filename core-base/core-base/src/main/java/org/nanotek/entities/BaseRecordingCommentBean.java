package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.RecordingComment;
import org.nanotek.entities.immutables.CommentEntity;

public interface BaseRecordingCommentBean
<K extends BaseBean<K,RecordingComment<?>>> 
extends
Base<K>,
BaseBean<K,RecordingComment<?>>,
BaseAreaCommentEntity<RecordingComment<?>>, 
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
