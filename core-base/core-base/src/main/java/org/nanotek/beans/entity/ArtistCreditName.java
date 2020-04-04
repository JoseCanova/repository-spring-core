package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.nanotek.entities.BaseArtistCreditNameEntity;
import org.nanotek.entities.MutableArtistCreditEntity;
import org.nanotek.entities.MutableArtistCreditNameJoinPhraseEntity;
import org.nanotek.entities.MutableArtistCreditNamePositionEntity;
import org.nanotek.entities.MutableArtistEntity;
import org.nanotek.entities.MutableNameEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Entity
@DiscriminatorValue(value="ArtistCreditName")
public class ArtistCreditName<K extends ArtistCreditName<K>> 
extends BrainzBaseEntity<K> 
implements  BaseArtistCreditNameEntity<K>, 
MutableArtistCreditEntity<ArtistCredit<?>>,
MutableArtistEntity<Artist<?>>,
MutableArtistCreditNamePositionEntity<ArtistCreditNamePosition<?>>,
MutableArtistCreditNameJoinPhraseEntity<String>,
MutableNameEntity<String>{

	private static final long serialVersionUID = -5124525598245692335L;

	/*
	 * @NotNull
	 * 
	 * @Column(name="artist_credit_name_id" , nullable=false) public Long
	 * artistCreditNameId;
	 */


	@NotBlank(groups = {CsvValidationGroup.class,Default.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String name;


	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String k) {
		this.name = k;
	}
	
	@NotNull(groups = {CsvValidationGroup.class,Default.class})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artist_credit_id" , insertable = true , nullable = true, referencedColumnName = "id")
	public  ArtistCredit<?> artistCredit;
	
	@NotNull(groups = {CsvValidationGroup.class,Default.class})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artistid" , insertable = true , nullable = true, referencedColumnName = "id")
	public  Artist<?> artist;
	
	@OneToOne
	@JoinTable(
			  name = "artist_credit_name_position_join", 
			  joinColumns = @JoinColumn(name = "artist_credit_name" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "position_id",referencedColumnName = "id"))
	public ArtistCreditNamePosition<?> artistCreditNamePosition; 
	
	@Column(name="artist_credit_name_join_phrase" ,nullable=true,insertable=true,columnDefinition = "TEXT NOT NULL DEFAULT ''")
	public String artistCreditNameJoinPhrase;

	
	public ArtistCreditName() {
	}
	
	public ArtistCreditName(@NotBlank String name) {
		this.name = name;
	}
	

	public ArtistCredit<?> getArtistCredit() {
		return artistCredit;
	}

	public void setArtistCredit(ArtistCredit<?> artistCredit) {
		this.artistCredit = artistCredit;
	}

	public Artist<?> getArtist() {
		return artist;
	}
	
	public void setArtist(Artist<?> artist) {
		this.artist = artist;
	}
	

	@Override
	public String getArtistCreditNameJoinPhrase() {
		return artistCreditNameJoinPhrase;
	}

	@Override
	public void setArtistCreditNameJoinPhrase(String k) {
		this.artistCreditNameJoinPhrase = k;		
	}

	public ArtistCreditNamePosition<?> getArtistCreditNamePosition() {
		return artistCreditNamePosition;
	}

	public void setArtistCreditNamePosition(ArtistCreditNamePosition<?> artistCreditNamePosition) {
		this.artistCreditNamePosition = artistCreditNamePosition;
	}
	
}
