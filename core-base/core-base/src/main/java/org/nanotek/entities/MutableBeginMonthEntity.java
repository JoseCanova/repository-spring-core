package org.nanotek.entities;

import java.io.Serializable;

public interface MutableBeginMonthEntity<K extends Serializable> extends BeginMonthEntity<K>{

	void setBeginMonth(K k);
	
}
