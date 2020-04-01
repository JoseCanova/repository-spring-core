package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.entities.BaseArtistCreditBean;
import org.nanotek.entities.BaseArtistCreditCountBean;
import org.nanotek.entities.BaseArtistCreditRefCountBean;
import org.nanotek.proxy.ProxyBase;

@SuppressWarnings("serial")
public class ArtistCreditBean
<K extends BaseBean<ArtistCreditBean<K>,ArtistCredit<?>>> 
extends ProxyBase<ArtistCreditBean<K>,ArtistCredit<?>>
implements BaseArtistCreditBean<ArtistCreditBean<K>>{

	
	
	
	
	public ArtistCreditBean() {
		super((Class<? extends ArtistCredit<?>>) ArtistCredit.class);
	}
	
	
	public ArtistCreditBean(Class<? extends ArtistCredit<?>> class1) {
		super(class1);
	}

	@Override
	public void setArtistCreditCount(BaseArtistCreditCountBean<?> k) {
	}

	@Override
	public BaseArtistCreditCountBean<?> getArtistCreditCount() {
		return null;
	}

	@Override
	public void setArtistCreditRefCount(BaseArtistCreditRefCountBean<?> k) {
	}

	@Override
	public BaseArtistCreditRefCountBean<?> getArtistCreditRefCount() {
		return null;
	}


//	
//	@NotNull
//	Long artistCreditId; 
//	@NotNull
//	String name; 
//	@NotNull
//	Integer artistCount;
//	@NotNull
//	Integer refCount; 
//	@NotNull
//	String created; 
//	@NotNull
//	Integer editsPending;
	
	
	
}
