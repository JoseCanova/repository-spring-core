package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseAliasLocale;
import org.nanotek.entities.immutables.LocaleEntity;

public interface BaseReleaseAliasLocaleBean
<K extends BaseBean<K,ReleaseAliasLocale<?>>>
extends Base<K>,BaseBean<K,ReleaseAliasLocale<?>>,
MutableLocaleEntity<String>{

	
	@Override
	default String getLocale() {
		return read(LocaleEntity.class).map(l->String.class.cast(l)).orElse("");
	}
	
	@Override
	default void setLocale(String k) {
		write(MutableLocaleEntity.class,k);
	}
	
}
