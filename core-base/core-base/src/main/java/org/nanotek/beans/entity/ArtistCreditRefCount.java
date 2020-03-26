package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseArtistCreditRefCountEntity;
import org.nanotek.entities.MutableArtistCreditEntity;

@Entity
@DiscriminatorValue(value = "ArtistCreditRefCount")
public class ArtistCreditRefCount<E extends ArtistCreditRefCount<E>> extends LongCountBase<E>
implements BaseArtistCreditRefCountEntity<E>,
MutableArtistCreditEntity<ArtistCredit<?>>{

	private static final long serialVersionUID = -4592756381355445004L;
	
	@NotNull	
	@OneToOne(optional = false , mappedBy = "refCount")
	public ArtistCredit<?> artistCredit;

	public ArtistCreditRefCount() {
	}

	public ArtistCreditRefCount(@NotNull Long count) {
		super(count);
	}

	public ArtistCredit<?> getArtistCredit() {
		return artistCredit;
	}

	public void setArtistCredit(ArtistCredit<?> artistCredit) {
		this.artistCredit = artistCredit;
	}
	
}
