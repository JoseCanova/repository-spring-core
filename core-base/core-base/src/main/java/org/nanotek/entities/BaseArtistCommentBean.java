package org.nanotek.entities;

import javax.validation.constraints.NotNull;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistCommentBean;
import org.nanotek.beans.entity.ArtistComment;
import org.nanotek.entities.immutables.CommentEntity;

public interface BaseArtistCommentBean
<K extends BaseBean<K,ArtistComment<?>>> 
extends 
Base<K>,
BaseBean<K,ArtistComment<?>>,
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
		ArtistCommentBean bean = new ArtistCommentBean();
		bean.setComment("this is a  comment");
		System.out.println(bean.getComment());
	}
}
