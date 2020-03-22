package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface LongIdSortNameEntityBase<T extends Serializable> extends LongIdEntityBase {

	T getSortName();
	
}
