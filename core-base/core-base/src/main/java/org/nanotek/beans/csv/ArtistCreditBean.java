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

	
	private BaseArtistCreditCountBean<?> artistCreditCount;
	private BaseArtistCreditRefCountBean<?> artistCreditRefCount;
	
	public ArtistCreditBean() {
		super(castClass() );
		postConstruct();
	}
	
	private void postConstruct() {
		artistCreditCount = new ArtistCreditCountBean<>();
		artistCreditRefCount = new ArtistCreditRefCountBean<>();
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistCredit<?>> castClass() {
		return (Class<? extends ArtistCredit<?>>) 
				ArtistCredit.class.
				asSubclass(ArtistCredit.class);
	}
	
	
	public ArtistCreditBean(Class<? extends ArtistCredit<?>> class1) {
		super(class1);
		postConstruct();
	}

	@Override
	public void setArtistCreditCount(BaseArtistCreditCountBean<?> k) {
		this.artistCreditCount = k;
	}

	@Override
	public BaseArtistCreditCountBean<?> getArtistCreditCount() {
		return artistCreditCount;
	}

	@Override
	public void setArtistCreditRefCount(BaseArtistCreditRefCountBean<?> k) {
		this.artistCreditRefCount = k;
	}

	@Override
	public BaseArtistCreditRefCountBean<?> getArtistCreditRefCount() {
		return artistCreditRefCount;
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
