package org.nanotek.entities;

import java.io.Serializable;

public interface MutableIdBase<K extends Serializable> extends MutableBase<K>{

	void setId(K k);
	
}
