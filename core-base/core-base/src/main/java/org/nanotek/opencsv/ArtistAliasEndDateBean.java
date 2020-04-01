package org.nanotek.opencsv;

import org.nanotek.BaseBean;
import  org.nanotek.beans.entity.*;
import org.nanotek.entities.BaseArtistAliasEndDateBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistAliasEndDateBean<K extends BaseBean<K,ArtistAliasEndDate<?>>> 
extends ProxyBase<K, ArtistAliasEndDate<?>>
implements BaseArtistAliasEndDateBean<K>{

	private static final long serialVersionUID = 4332333591206455409L;

	public ArtistAliasEndDateBean() {
		super(castClass());
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistAliasEndDate<?>> castClass() {
		return (Class<? extends ArtistAliasEndDate<?>>) ArtistAliasEndDate.class.asSubclass(ArtistAliasEndDate.class);
	}
	
	public ArtistAliasEndDateBean(Class<ArtistAliasEndDate<?>> clazz) {
		super(clazz);
	}

}
