package org.nanotek.entities;

import java.io.Serializable;

public interface MutableLenght<K  extends Serializable> {

	void setLength(K length);
	
}
