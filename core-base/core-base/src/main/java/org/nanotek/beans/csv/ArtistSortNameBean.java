package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistSortName;
import org.nanotek.entities.BaseArtistSortNameBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistSortNameBean 
<K extends BaseBean<ArtistSortNameBean<K>,ArtistSortName<?>>>
extends ProxyBase<ArtistSortNameBean<K>,ArtistSortName<?>>
implements BaseArtistSortNameBean<ArtistSortNameBean<K>> {

	private static final long serialVersionUID = 4896420012561668248L;

	public ArtistSortNameBean() {
		super(castClass());
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistSortName<?>>castClass() {
		return (Class<? extends ArtistSortName<?>>) 
				ArtistSortName.class.
				asSubclass(ArtistSortName.class);
	}
	
	public ArtistSortNameBean(Class<? extends ArtistSortName<?>> class1) {
		super(class1);
	}
	
	

}
