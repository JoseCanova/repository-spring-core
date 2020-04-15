package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.nanotek.entities.BaseArtistEndDateEntity;
import org.nanotek.entities.MutableEndDateEntity;
import org.nanotek.entities.MutableEndDayEntity;
import org.nanotek.entities.MutableEndMonthEntity;
import org.nanotek.entities.MutableEndYearEntity;

@Entity
@DiscriminatorValue("ArtistEndDate")
public class ArtistEndDate<K extends ArtistEndDate<K>> 
extends EndDateBase<K> 
implements BaseArtistEndDateEntity<K>,
MutableEndDateEntity<Integer>,
MutableEndYearEntity<Integer>,MutableEndMonthEntity<Integer>,MutableEndDayEntity<Integer>{

	private static final long serialVersionUID = 5843448446493083180L;

	public ArtistEndDate() {
	}

	public ArtistEndDate(Integer year, Integer month, Integer day) {
		super(year, month, day);
	}

	public ArtistEndDate(Integer year, Integer month) {
		super(year, month);
	}

	public ArtistEndDate(Integer year) {
		super(year);
	}
	
}
