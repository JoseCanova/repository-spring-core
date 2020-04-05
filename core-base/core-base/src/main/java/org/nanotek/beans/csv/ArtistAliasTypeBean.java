package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistAliasType;
import org.nanotek.entities.BaseArtistAliasTypeBean;
import org.nanotek.proxy.ProxyBase;

/*
 * public class AreaBean
<K extends BaseBean<K,Area<?>>> 
extends CsvBaseBean<Area<?>>
implements BaseAreaBean<K>
 * 
 */
public class ArtistAliasTypeBean
<K extends BaseBean<ArtistAliasTypeBean<K>,ArtistAliasType<?>>> 
extends ProxyBase<ArtistAliasTypeBean<K>,ArtistAliasType<?>>
implements  BaseArtistAliasTypeBean<ArtistAliasTypeBean<K>>{

	private static final long serialVersionUID = 901207660901713562L;

	
	public ArtistAliasTypeBean() {
		super(castClass());
	}
	
	
	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistAliasType<?>>castClass() {
		return (Class<? extends ArtistAliasType<?>>) 
				ArtistAliasType.class.
				asSubclass(ArtistAliasType.class);
	}
	
	public ArtistAliasTypeBean(Class<? extends ArtistAliasType<?>> class1) {
		super(class1);
	}
	
	
}
