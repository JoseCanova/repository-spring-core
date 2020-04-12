package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistCreditedNamePosition;
import org.nanotek.entities.BaseArtistCreditedNamePositionBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistCreditNamePositionBean 
<K extends BaseBean<ArtistCreditNamePositionBean<K>,ArtistCreditedNamePosition<?>>> 
extends ProxyBase<ArtistCreditNamePositionBean<K>,ArtistCreditedNamePosition<?>>
implements BaseArtistCreditedNamePositionBean<ArtistCreditNamePositionBean<K>>
{

	
	private static final long serialVersionUID = 2980096849129094415L;

	public ArtistCreditNamePositionBean() {
		super(castClass());
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistCreditedNamePosition<?>> castClass() {
		return (Class<? extends ArtistCreditedNamePosition<?>>) 
				ArtistCreditedNamePosition.class.
				asSubclass(ArtistCreditedNamePosition.class);
	}

	public ArtistCreditNamePositionBean(Class<? extends ArtistCreditedNamePosition<?>> class1) {
		super(class1);
	}
	
	
}
