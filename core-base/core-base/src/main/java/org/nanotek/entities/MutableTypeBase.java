package org.nanotek.entities;

import java.io.Serializable;

public interface MutableTypeBase<K extends Serializable> {

	void setType(K k);
	
}
