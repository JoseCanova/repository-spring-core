package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.LanguageIdEntity;

public interface MutableLanguageIdEntity <K extends Serializable> extends LanguageIdEntity<K>{

	void setLanguageiId(K k);
}
