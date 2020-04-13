package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseCommentEntity;

public interface MutableReleaseCommentEntity<T>  extends ReleaseCommentEntity<T>{
	void setReleaseComment(T t);

}
