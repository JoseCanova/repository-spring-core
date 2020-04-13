package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseArtistCreditedNamePositionEntity;
import org.nanotek.entities.MutableArtistCreditNameEntity;
import org.nanotek.entities.MutableArtistCreditedNameEntity;

@Entity
@DiscriminatorValue(value ="ArtistCreditNamePosition" )
public class ArtistCreditedNamePosition<T extends ArtistCreditedNamePosition<T>> 
extends LongPositionBase<T> 
implements BaseArtistCreditedNamePositionEntity,
MutableArtistCreditedNameEntity<ArtistCreditedName<?>>{

	private static final long serialVersionUID = -482152069144029668L;
	
	@NotNull
	@OneToOne(mappedBy="artistCreditNamePosition")
	private ArtistCreditedName<?> artistCreditedName;
	
	
	public ArtistCreditedNamePosition() {}
	
	
	public ArtistCreditedNamePosition(@NotNull ArtistCreditedName<?> artistCreditName) {
		super();
		this.artistCreditedName = artistCreditName;
	}


	public ArtistCreditedName<?> getArtistCreditedName() {
		return artistCreditedName;
	}


	public void setArtistCreditedName(ArtistCreditedName<?> artistCretditName) {
		this.artistCreditedName = artistCretditName;
	}
	

}
