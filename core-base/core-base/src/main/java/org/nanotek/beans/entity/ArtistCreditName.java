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

import org.nanotek.entities.BaseArtistCreditNameEntity;
import org.nanotek.entities.MutableArtistCreditEntity;
import org.nanotek.entities.MutableArtistCreditNameJoinPhraseEntity;
import org.nanotek.entities.MutableArtistCreditNamePositionEntity;
import org.nanotek.entities.MutableArtistEntity;
import org.nanotek.entities.MutableNameEntity;
import org.nanotek.entities.MutablePositionEntity;
import org.nanotek.entities.MutatleArtistCreditNameIdEntity;

@Entity
@DiscriminatorValue(value="ArtistCreditName")
public class ArtistCreditName<K extends ArtistCreditName<K>> 
extends BrainzBaseEntity<K> 
implements  BaseArtistCreditNameEntity<K>,
MutatleArtistCreditNameIdEntity<Long>, 
MutableArtistCreditEntity<ArtistCredit<?>>,
MutableArtistEntity<Artist<?>>,
MutableArtistCreditNamePositionEntity<ArtistCreditNamePosition<?>>,
MutableArtistCreditNameJoinPhraseEntity<String>,
MutableNameEntity<String>{

	private static final long serialVersionUID = -5124525598245692335L;

	@NotNull
	@Column(name="artist_credit_name_id" , nullable=false)
	public Long artistCreditNameId;


	@NotNull
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
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artist_credit_id" , insertable = true , nullable = true, referencedColumnName = "id")
	public  ArtistCredit<?> artistCredit;
	
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
	
	public ArtistCreditName(@NotBlank String name, @NotNull Long artistCreditNameId) {
		this.name = name;
		this.artistCreditNameId = artistCreditNameId;
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
	public Long getArtistCreditNameId() {
		return artistCreditNameId;
	}
	@Override
	public void setArtistCreditNameId(Long k) {
			this.artistCreditNameId = k;
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
