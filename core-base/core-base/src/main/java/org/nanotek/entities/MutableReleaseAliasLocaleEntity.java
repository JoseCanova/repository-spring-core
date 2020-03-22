package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ReleaseAliasLocaleEntity;

public interface MutableReleaseAliasLocaleEntity<K extends Serializable> extends ReleaseAliasLocaleEntity<K>{
	void setReleaseAliasLocale(K k);
}
