package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface CommentEntity<K extends Serializable> {

	K getComment();
	
}
