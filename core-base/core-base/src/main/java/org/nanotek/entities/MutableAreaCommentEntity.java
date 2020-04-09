package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.AreaCommentEntity;

public interface MutableAreaCommentEntity<K> extends AreaCommentEntity<K> {

	void setAreaComment(K k);
	
}
