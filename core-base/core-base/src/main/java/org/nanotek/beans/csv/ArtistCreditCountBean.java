package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistCreditCount;
import org.nanotek.entities.BaseArtistCreditCountBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistCreditCountBean 
<K extends BaseBean<ArtistCreditCountBean<K>,ArtistCreditCount<?>>> 
extends ProxyBase<ArtistCreditCountBean<K>,ArtistCreditCount<?>>
implements BaseArtistCreditCountBean<ArtistCreditCountBean<K>>{

	private static final long serialVersionUID = 131002699104285572L;

	public ArtistCreditCountBean() {
		super(castClass());
	}
	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistCreditCount<?>> castClass() {
		return (Class<? extends ArtistCreditCount<?>>) 
				ArtistCreditCount.class.
				asSubclass(ArtistCreditCount.class);
	}
	
	
	
}
