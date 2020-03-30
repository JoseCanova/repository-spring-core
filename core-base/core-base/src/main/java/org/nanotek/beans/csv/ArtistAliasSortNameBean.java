package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistAliasSortName;
import org.nanotek.entities.BaseArtistAliasSortNameBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistAliasSortNameBean 
<K extends BaseBean<ArtistAliasSortNameBean<K>,ArtistAliasSortName<?>>> 
extends ProxyBase<ArtistAliasSortNameBean<K>,ArtistAliasSortName<?>>
implements BaseArtistAliasSortNameBean<ArtistAliasSortNameBean<K>>{

	private static final long serialVersionUID = -8288974192757187546L;

	public ArtistAliasSortNameBean() {
		super((Class<? extends ArtistAliasSortName<?>>) ArtistAliasSortName.class);
	}

	public ArtistAliasSortNameBean(Class<ArtistAliasSortName<?>> clazz) {
		super(clazz);
	}
}
