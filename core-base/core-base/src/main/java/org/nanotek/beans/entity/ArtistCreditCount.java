package org.nanotek.beans.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.nanotek.BaseEntity;
import org.nanotek.entities.BaseArtistCreditCountEntity;
import org.nanotek.entities.MutableArtistCreditEntity;

@Entity
@DiscriminatorValue(value = "ArtistCreditCount")
public class ArtistCreditCount<K extends ArtistCreditCount<K>> extends LongCountBase<K> 
													   implements BaseArtistCreditCountEntity<K> , 
													   			  MutableArtistCreditEntity<ArtistCredit<?>>{

	private static final long serialVersionUID = 2246716928420528795L;

	@NotNull
	@OneToOne(optional = false , mappedBy = "artistCount")
	public ArtistCredit<?> artistCredit;
	
	public ArtistCreditCount() {
	}

	public ArtistCreditCount(@NotNull Long count) {
		super(count);
	}

	public ArtistCredit<?> getArtistCredit() {
		return artistCredit;
	}

	public void setArtistCredit(ArtistCredit<?> artistCredit) {
		this.artistCredit = artistCredit;
	}
	
}
