package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.nanotek.entities.BaseArtistAliasBeginDateEntity;

@Entity
@DiscriminatorValue("ArtistAliasBeginDate")
public class ArtistAliasBeginDate<K extends ArtistAliasBeginDate<K>> extends DatableBase<K ,Integer,Integer,Integer> 
								  implements BaseArtistAliasBeginDateEntity<K>{

	private static final long serialVersionUID = -9175061452241841539L;

	public ArtistAliasBeginDate() {
	}

	public ArtistAliasBeginDate(Integer year, Integer month, Integer day) {
		super(year, month, day);
	}

	public ArtistAliasBeginDate(Integer year, Integer month) {
		super(year, month);
	}

	public ArtistAliasBeginDate(Integer year) {
		super(year);
	}
	
}
