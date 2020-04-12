package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseArtistCreditNamePositionEntity;
import org.nanotek.entities.MutableArtistCreditNameEntity;

@Entity
@DiscriminatorValue(value ="ArtistCreditNamePosition" )
public class ArtistCreditNamePosition<T extends ArtistCreditNamePosition<T>> 
extends LongPositionBase<T> 
implements BaseArtistCreditNamePositionEntity,
MutableArtistCreditNameEntity<ArtistCreditedName<?>>{

	private static final long serialVersionUID = -482152069144029668L;
	
	@NotNull
	@OneToOne(mappedBy="artistCreditNamePosition")
	private ArtistCreditedName<?> artistCreditName;
	
	
	public ArtistCreditNamePosition() {}
	
	
	public ArtistCreditNamePosition(@NotNull ArtistCreditedName<?> artistCreditName) {
		super();
		this.artistCreditName = artistCreditName;
	}


	public ArtistCreditedName<?> getArtistCreditName() {
		return artistCreditName;
	}


	public void setArtistCreditName(ArtistCreditedName<?> artistCretditName) {
		this.artistCreditName = artistCretditName;
	}
	

}
