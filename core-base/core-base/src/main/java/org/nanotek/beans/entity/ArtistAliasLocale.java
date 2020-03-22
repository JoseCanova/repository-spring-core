package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.nanotek.entities.BaseArtistAliasLocaleEntity;

@Entity
@DiscriminatorValue(value="ArtistAliasLocale")
public class ArtistAliasLocale<K extends ArtistAliasLocale<K>> extends LocaleBase<K> implements 
															 BaseArtistAliasLocaleEntity<K> {

	private static final long serialVersionUID = -6819342630275200151L;

	@OneToOne(mappedBy="artistAliasLocale")
	public ArtistAlias<?> artistAlias;
	
	public ArtistAliasLocale() {
	}

	public ArtistAliasLocale(@NotBlank String locale) {
		super(locale);
	}

	public ArtistAlias<?> getArtistAlias() {
		return artistAlias;
	}

	public void setArtistAlias(ArtistAlias<?> artistAlias) {
		this.artistAlias = artistAlias;
	}

}
