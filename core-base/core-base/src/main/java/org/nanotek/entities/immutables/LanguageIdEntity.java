package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface LanguageIdEntity<K extends Serializable> {

	K getLanguageId();
	
}
