package org.nanotek.entities;

import org.nanotek.entities.immutables.AreaCommentEntity;

public interface MutableAreaCommentEntity<K> extends AreaCommentEntity<K> {

	void setAreaComment(K k);
	
}
