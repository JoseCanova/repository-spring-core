package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistCreditName;
import org.nanotek.entities.BaseArtistBean;
import org.nanotek.entities.BaseArtistCreditBean;
import org.nanotek.entities.BaseArtistCreditNameBean;
import org.nanotek.entities.BaseArtistCreditNamePositionBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistCreditNameBean
<K extends BaseBean<ArtistCreditNameBean<K>,ArtistCreditName<?>>> 
extends ProxyBase<ArtistCreditNameBean<K>,ArtistCreditName<?>>
implements BaseArtistCreditNameBean<ArtistCreditNameBean<K>>{


	private static final long serialVersionUID = -625201514069517695L;

	private BaseArtistCreditBean<?> artistCredit;
	
	private BaseArtistBean<?> artist;
	
	private BaseArtistCreditNamePositionBean<?> artistCreditNamePosition;
	
//	private Long artistCreditId;
//	
//	private Long position;
//	
//	private Long artistId; 
//	
//	private String name; 
//	
//	private String joinPhrase;

	
	public ArtistCreditNameBean() {
		super( castClass());
		postConstruct();
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistCreditName<?>> castClass() {
		return (Class<? extends ArtistCreditName<?>>) 
				ArtistCreditName.class.
				asSubclass(ArtistCreditName.class);
	}
	
	
	public ArtistCreditNameBean(Class<? extends ArtistCreditName<?>> class1) {
		super(class1);
		postConstruct();
	}

	private void postConstruct() {
		artistCredit = new ArtistCreditBean<> ();
		artist = new ArtistBean<>();
		artistCreditNamePosition = new ArtistCreditNamePositionBean<>(); 
	}

	public BaseArtistCreditBean<?> getArtistCredit() {
		return artistCredit;
	}

	public void setArtistCredit(BaseArtistCreditBean<?> artistCredit) {
		this.artistCredit = artistCredit;
	}

	public BaseArtistBean<?> getArtist() {
		return artist;
	}

	public void setArtist(BaseArtistBean<?> artist) {
		this.artist = artist;
	}

	public BaseArtistCreditNamePositionBean<?> getArtistCreditNamePosition() {
		return artistCreditNamePosition;
	}

	public void setArtistCreditNamePosition(BaseArtistCreditNamePositionBean<?> artistCreditNamePosition) {
		this.artistCreditNamePosition = artistCreditNamePosition;
	}

	
}
