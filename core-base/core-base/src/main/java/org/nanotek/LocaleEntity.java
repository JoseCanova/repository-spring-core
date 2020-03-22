package org.nanotek;

import java.io.Serializable;

public interface LocaleEntity<K extends Serializable> {

	K getLocale();
	
}
