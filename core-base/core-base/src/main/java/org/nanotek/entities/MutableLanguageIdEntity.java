package org.nanotek.entities;

import org.nanotek.entities.immutables.LanguageIdEntity;

public interface MutableLanguageIdEntity <K> extends LanguageIdEntity<K>{

	void setLanguageId(K k);
}
