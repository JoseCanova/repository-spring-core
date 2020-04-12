package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistCreditedName;
import org.nanotek.entities.BaseArtistBean;
import org.nanotek.entities.BaseArtistCreditBean;
import org.nanotek.entities.BaseArtistCreditedNameBean;
import org.nanotek.entities.BaseArtistCreditNamePositionBean;
import org.nanotek.proxy.ProxyBase;

public class ArtistCreditedNameBean
<K extends BaseBean<ArtistCreditedNameBean<K>,ArtistCreditedName<?>>> 
extends ProxyBase<ArtistCreditedNameBean<K>,ArtistCreditedName<?>>
implements BaseArtistCreditedNameBean<ArtistCreditedNameBean<K>>{


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

	
	public ArtistCreditedNameBean() {
		super( castClass());
		postConstruct();
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends ArtistCreditedName<?>> castClass() {
		return (Class<? extends ArtistCreditedName<?>>) 
				ArtistCreditedName.class.
				asSubclass(ArtistCreditedName.class);
	}
	
	
	public ArtistCreditedNameBean(Class<? extends ArtistCreditedName<?>> class1) {
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
