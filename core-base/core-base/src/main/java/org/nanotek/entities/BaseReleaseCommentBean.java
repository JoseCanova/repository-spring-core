package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ReleaseCommentBean;
import org.nanotek.beans.entity.ReleaseComment;
import org.nanotek.entities.immutables.CommentEntity;

public interface BaseReleaseCommentBean
<K extends BaseBean<K,ReleaseComment<?>>> 
extends 
Base<K>,
BaseBean<K,ReleaseComment<?>>,
MutableCommentEntity<String>
{
	
	@Override
	default String getComment() {
		return read(CommentEntity.class).map(c->String.class.cast(c)).orElse("");
	}
	
	@Override
	default void setComment(String k) {
		write(MutableCommentEntity.class,k);
	}
	
	public static void main(String[] args) {
		ReleaseCommentBean bean = new ReleaseCommentBean();
		bean.setComment("this is a  comment");
		System.out.println(bean.getComment());
	}

}
