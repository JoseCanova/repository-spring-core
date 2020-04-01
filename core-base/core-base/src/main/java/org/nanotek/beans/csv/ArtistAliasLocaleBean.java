package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.ArtistAliasLocale;
import org.nanotek.entities.BaseArtistAliasLocaleBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistAliasLocaleBean 
<K extends BaseBean<K,ArtistAliasLocale<?>>>
extends ProxyBase<K,ArtistAliasLocale<?>>
implements BaseArtistAliasLocaleBean<K>{

	private static final long serialVersionUID = -2704664983561986684L;

	public ArtistAliasLocaleBean() {
		super(castClass());
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistAliasLocale<?>> castClass() {
		return (Class<? extends ArtistAliasLocale<?>>) ArtistAliasLocale.class.asSubclass(ArtistAliasLocale.class);
	}

	public ArtistAliasLocaleBean(Class<? extends ArtistAliasLocale<?>> class1) {
		super(class1);
	}

	
}
