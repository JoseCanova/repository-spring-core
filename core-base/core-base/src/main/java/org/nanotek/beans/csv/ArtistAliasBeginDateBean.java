package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.ArtistAliasBeginDate;
import org.nanotek.entities.BaseArtistAliasBeginDateBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistAliasBeginDateBean
<K extends BaseBean<K,ArtistAliasBeginDate<?>>> 
extends ProxyBase<K,ArtistAliasBeginDate<?>>
implements BaseArtistAliasBeginDateBean<K>{

	private static final long serialVersionUID = 1825065026844937439L;

	public ArtistAliasBeginDateBean() {
		super( castClass());
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistAliasBeginDate<?>> castClass() {
		return (Class<? extends ArtistAliasBeginDate<?>>) 
				ArtistAliasBeginDate.class.
				asSubclass(ArtistAliasBeginDate.class);
	}
	
	public ArtistAliasBeginDateBean(Class<? extends ArtistAliasBeginDate<?>> class1) {
		super(class1);
	}
	
}
