package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.LocaleEntity;

public interface MutableLocaleEntity<K extends Serializable> extends LocaleEntity<K>{

	void setLocale(K k);
}
