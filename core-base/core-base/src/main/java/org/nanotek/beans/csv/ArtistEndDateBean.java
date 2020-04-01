package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.ArtistEndDate;
import org.nanotek.entities.BaseArtistEndDateBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistEndDateBean
<K extends BaseBean<ArtistEndDateBean<K>,ArtistEndDate<?>>> 
extends ProxyBase<ArtistEndDateBean<K>,ArtistEndDate<?>>
implements BaseArtistEndDateBean<ArtistEndDateBean<K>> {

	
	private static final long serialVersionUID = -3730767802005710713L;

	public ArtistEndDateBean() {
		super(castClass());
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistEndDate<?>> castClass() {
		return (Class<? extends ArtistEndDate<?>>) 
				ArtistEndDate.class.
				asSubclass(ArtistEndDate.class);
	}
	
	public ArtistEndDateBean(Class<? extends ArtistEndDate<?>> class1) {
		super(class1);
	}

}
