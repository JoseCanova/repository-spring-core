package org.nanotek.beans.entity;

import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseArtistCreditedNameJoinPhraseEntity;
import org.nanotek.entities.MutableArtistCreditedNameEntity;

public class ArtistCreditedNameJoinPhrase<K extends ArtistCreditedNameJoinPhrase<K>> 
extends JoinPhraseBase<String,ArtistCreditedNameJoinPhrase<?>> 
implements BaseArtistCreditedNameJoinPhraseEntity<K>,
MutableArtistCreditedNameEntity<ArtistCreditedName<?>> {

	private static final long serialVersionUID = 5258524420279200258L;
	
	public ArtistCreditedName<?> artistCreditedName;

	public ArtistCreditedNameJoinPhrase() {
		super();
	}

	public ArtistCreditedNameJoinPhrase(@NotNull String joinPhrase) {
		super(joinPhrase);
	}

	public ArtistCreditedName<?> getArtistCreditedName() {
		return artistCreditedName;
	}

	public void setArtistCreditedName(ArtistCreditedName<?> artistCreditName) {
		this.artistCreditedName = artistCreditName;
	}
	
}
