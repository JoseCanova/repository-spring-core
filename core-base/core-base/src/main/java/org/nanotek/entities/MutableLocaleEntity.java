package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.LocaleEntity;

public interface MutableLocaleEntity<K> extends LocaleEntity<K>{

	void setLocale(K k);
}
