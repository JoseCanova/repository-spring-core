package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.nanotek.entities.MutableArtistEntity;


@Entity
@DiscriminatorValue(value="ArtistSortName")
public class ArtistSortName<K extends ArtistSortName<K>> extends SortNameBase<K> 
implements MutableArtistEntity<Artist<?>> {

	private static final long serialVersionUID = 492175239931176224L;

	@OneToOne(mappedBy = "artistSortName")
	private Artist<?> artist;
	
	
	
	public ArtistSortName(@NotBlank String sortName) {
		super(sortName);
	}

	public ArtistSortName(Artist<?> artist) {
		super();
		this.artist = artist;
	}

	public ArtistSortName() {
	}

	public Artist<?> getArtist() {
		return artist;
	}

	public void setArtist(Artist<?> artist) {
		this.artist = artist;
	}
	
}
