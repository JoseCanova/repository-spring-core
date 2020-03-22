package org.nanotek;

import java.io.Serializable;

public interface SortNameBaseEntity<K extends Serializable> {
	
	K getSortName();
}
