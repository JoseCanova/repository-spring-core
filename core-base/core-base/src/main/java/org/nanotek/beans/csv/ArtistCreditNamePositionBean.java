package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistCreditNamePosition;
import org.nanotek.entities.BaseArtistCreditNamePositionBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistCreditNamePositionBean 
<K extends BaseBean<ArtistCreditNamePositionBean<K>,ArtistCreditNamePosition<?>>> 
extends ProxyBase<ArtistCreditNamePositionBean<K>,ArtistCreditNamePosition<?>>
implements BaseArtistCreditNamePositionBean<ArtistCreditNamePositionBean<K>>
{

	
	private static final long serialVersionUID = 2980096849129094415L;

	public ArtistCreditNamePositionBean() {
		super(castClass());
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistCreditNamePosition<?>> castClass() {
		return (Class<? extends ArtistCreditNamePosition<?>>) 
				ArtistCreditNamePosition.class.
				asSubclass(ArtistCreditNamePosition.class);
	}

	public ArtistCreditNamePositionBean(Class<? extends ArtistCreditNamePosition<?>> class1) {
		super(class1);
	}
	
	
}
