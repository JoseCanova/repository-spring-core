package org.nanotek.entities;

import java.io.Serializable;

public interface MutableIdBase<K> extends MutableBase<K>{

	void setId(K k);
	
}
