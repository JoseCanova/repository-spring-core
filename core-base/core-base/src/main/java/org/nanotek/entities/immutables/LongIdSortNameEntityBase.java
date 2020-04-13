package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface LongIdSortNameEntityBase<T> extends LongIdEntityBase {

	T getSortName();
	
}
