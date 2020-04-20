package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseArtistCreditRefCountEntity;
import org.nanotek.entities.MutableRefCountEntity;

@Entity
@DiscriminatorValue(value = "ArtistCreditRefCount")
public class ArtistCreditRefCount<E extends ArtistCreditRefCount<E>> extends LongCountBase<E>
implements BaseArtistCreditRefCountEntity<E>,
MutableRefCountEntity<Long>{

	private static final long serialVersionUID = -4592756381355445004L;
	

	public ArtistCreditRefCount() {
	}

	public ArtistCreditRefCount(@NotNull Long count) {
		super(count);
	}

	@Override
	public Long getRefCount() {
		return getCount();
	}

	@Override
	public void setRefCount(Long k) {
		setCount(k);
	}
	
}
