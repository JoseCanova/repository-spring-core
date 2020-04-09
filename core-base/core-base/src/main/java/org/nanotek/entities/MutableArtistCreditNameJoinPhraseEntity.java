package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.entities.immutables.ArtistCreditNameJoinPhraseEntity;

public interface MutableArtistCreditNameJoinPhraseEntity<K> extends ArtistCreditNameJoinPhraseEntity<K> {
	
	void setArtistCreditNameJoinPhrase(K k);
  
}
