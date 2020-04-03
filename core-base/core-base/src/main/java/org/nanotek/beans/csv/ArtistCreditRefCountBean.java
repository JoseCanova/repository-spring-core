package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistCreditRefCount;
import org.nanotek.entities.BaseArtistCreditRefCountBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistCreditRefCountBean 
<K extends BaseBean<ArtistCreditRefCountBean<K>,ArtistCreditRefCount<?>>> 
extends ProxyBase<ArtistCreditRefCountBean<K>,ArtistCreditRefCount<?>>
implements BaseArtistCreditRefCountBean<ArtistCreditRefCountBean<K>>{

	private static final long serialVersionUID = 131002699104285572L;

	public ArtistCreditRefCountBean() {
		super(castClass());
	}
	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistCreditRefCount<?>> castClass() {
		return (Class<? extends ArtistCreditRefCount<?>>) 
				ArtistCreditRefCount.class.
				asSubclass(ArtistCreditRefCount.class);
	}
	
	
	
}
