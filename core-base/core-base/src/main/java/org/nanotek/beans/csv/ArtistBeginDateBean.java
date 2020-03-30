package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistBeginDate;
import org.nanotek.proxy.ProxyBase;

public class ArtistBeginDateBean 
<K extends BaseBean<ArtistBeginDateBean<K>,ArtistBeginDate<?>>>
extends ProxyBase<ArtistBeginDateBean<K>,ArtistBeginDate<?>>{

	private static final long serialVersionUID = -4636175302472127767L;

	public ArtistBeginDateBean() {
		super((Class<? extends ArtistBeginDate<?>>) ArtistBeginDate.class);
	}

}
