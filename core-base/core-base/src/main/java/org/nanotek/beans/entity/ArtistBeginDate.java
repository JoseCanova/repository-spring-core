package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.nanotek.entities.BaseArtistBeginDateEntity;

@Entity
@DiscriminatorValue("ArtistBeginDate")
public class ArtistBeginDate<K extends ArtistBeginDate<K>> 
extends DatableBase<K,Integer,Integer,Integer> 
implements BaseArtistBeginDateEntity<K>{

	private static final long serialVersionUID = 4339317283945952774L;

	public ArtistBeginDate() {
	}

	public ArtistBeginDate(Integer year, Integer month, Integer day) {
		super(year, month, day);
	}

	public ArtistBeginDate(Integer year, Integer month) {
		super(year, month);
	}

	public ArtistBeginDate(Integer year) {
		super(year);
	}

}
