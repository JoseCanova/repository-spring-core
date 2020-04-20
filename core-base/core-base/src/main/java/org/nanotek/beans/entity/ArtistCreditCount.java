package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseArtistCreditCountEntity;
import org.nanotek.entities.MutableArtistCreditEntity;

@Entity
@DiscriminatorValue(value = "ArtistCreditCount")
public class ArtistCreditCount
<K extends ArtistCreditCount<K>> extends LongCountBase<K> 
implements BaseArtistCreditCountEntity<K>{

	private static final long serialVersionUID = 2246716928420528795L;

	
	public ArtistCreditCount() {
	}

	public ArtistCreditCount(@NotNull Long count) {
		super(count);
	}

	
}
