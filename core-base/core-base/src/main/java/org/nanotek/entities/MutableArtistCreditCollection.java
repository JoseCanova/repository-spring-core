package org.nanotek.entities;

import org.nanotek.entities.immutables.ArtistCreditCollection;

public interface MutableArtistCreditCollection<C> extends ArtistCreditCollection<C>{

	void setArtistCredits(C c);
	
}
