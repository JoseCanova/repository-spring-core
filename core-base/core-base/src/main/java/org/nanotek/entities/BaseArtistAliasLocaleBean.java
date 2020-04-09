package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistAliasLocale;
import org.nanotek.entities.immutables.LocaleEntity;

public interface BaseArtistAliasLocaleBean<K extends BaseBean<K,ArtistAliasLocale<?>>>
extends Base<K>,BaseBean<K,ArtistAliasLocale<?>>,
MutableLocaleEntity<String>{

	
	@Override
	default String getLocale() {
		return read(LocaleEntity.class).map(l->String.class.cast(l)).orElse("");
	}
	
	@Override
	default void setLocale(String k) {
		write(MutableLanguageEntity.class,k);
	}
	
}
