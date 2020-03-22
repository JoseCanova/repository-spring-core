package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistCreditNameJoinPhraseEntity;

public interface MutableArtistCreditNameJoinPhraseEntity<K extends Serializable> extends ArtistCreditNameJoinPhraseEntity<K> {
	
	void setArtistCreditJoinPhrase(K k);
  
}
