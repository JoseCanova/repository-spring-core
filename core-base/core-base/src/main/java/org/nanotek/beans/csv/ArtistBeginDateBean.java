package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistBeginDate;
import org.nanotek.entities.BaseArtistBeginDateBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistBeginDateBean 
<K extends BaseBean<ArtistBeginDateBean<K>,ArtistBeginDate<?>>>
extends ProxyBase<ArtistBeginDateBean<K>,ArtistBeginDate<?>>
implements BaseArtistBeginDateBean<ArtistBeginDateBean<K>>{

	private static final long serialVersionUID = -4636175302472127767L;

	public ArtistBeginDateBean() {
		super(castClass());
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistBeginDate<?>> castClass() {
		return (Class<? extends ArtistBeginDate<?>>) 
				ArtistBeginDate.class.
				asSubclass(ArtistBeginDate.class);
	}
	
	public ArtistBeginDateBean(Class<? extends ArtistBeginDate<?>> class1) {
		super(class1);
	}
	
	

}
