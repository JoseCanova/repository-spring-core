package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.IdBase;

public interface MutableCommentBase<K extends IdBase<?,?>,S extends Serializable> {

	void setCommentBase(S k);
	
}
