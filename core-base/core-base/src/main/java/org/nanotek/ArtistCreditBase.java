package org.nanotek;

import org.nanotek.beans.entity.ArtistCredit;

public interface ArtistCreditBase<K extends ArtistCredit> {

	K getArtistCredit();
	
	void setArtistCredit(K id);
	
}
