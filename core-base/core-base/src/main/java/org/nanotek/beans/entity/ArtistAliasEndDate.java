package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.nanotek.entities.BaseArtistAliasEndDateEntity;

@Entity
@DiscriminatorValue("ArtistAliasEndDate")
public class ArtistAliasEndDate<K extends ArtistAliasEndDate<K>> extends DatableBase<K,Integer,Integer,Integer>
								implements BaseArtistAliasEndDateEntity<K>{

	private static final long serialVersionUID = 7987268027913627678L;

	public ArtistAliasEndDate() {
	}

	public ArtistAliasEndDate(Integer year, Integer month, Integer day) {
		super(year, month, day);
	}

	public ArtistAliasEndDate(Integer year, Integer month) {
		super(year, month);
	}

	public ArtistAliasEndDate(Integer year) {
		super(year);
	}
	
}
