package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.IdBase;

public interface MutableCommentBase<K extends IdBase<?,?>,S> {

	void setCommentBase(S k);
	
}
