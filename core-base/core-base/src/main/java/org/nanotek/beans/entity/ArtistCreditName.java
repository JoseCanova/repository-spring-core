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

import org.nanotek.BaseEntity;
import org.nanotek.entities.BaseArtistCreditNameEntity;
import org.nanotek.entities.MutableArtistCreditEntity;
import org.nanotek.entities.MutableArtistCreditNameJoinPhraseEntity;
import org.nanotek.entities.MutableArtistEntity;
import org.nanotek.entities.MutablePositionEntity;
import org.nanotek.entities.MutatbleArtistCreditNameIdEntity;

import com.sun.xml.bind.v2.model.core.ID;

@Entity
@DiscriminatorValue(value="ArtistCreditName")
public class ArtistCreditName<K extends ArtistCreditName<K>> 
extends LongIdName<K> 
implements  BaseArtistCreditNameEntity<K>,
MutatbleArtistCreditNameIdEntity<Long>, 
MutableArtistCreditEntity<ArtistCredit<?>>,
MutableArtistEntity<Artist<?>>,
MutablePositionEntity<ArtistCreditNamePosition<?>>,
MutableArtistCreditNameJoinPhraseEntity<String>{

	private static final long serialVersionUID = -5124525598245692335L;

	@NotNull
	@Column(name="artist_credit_name_id" , nullable=false)
	public Long artistCreditNameId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artist_credit_id" , insertable = true , nullable = true, referencedColumnName = "id")
	public ArtistCredit<?> artistCredit;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artistid" , insertable = true , nullable = true, referencedColumnName = "id")
	public Artist<?> artist;
	
	@OneToOne
	@JoinTable(
			  name = "artist_credit_name_position_join", 
			  joinColumns = @JoinColumn(name = "artist_credit_name" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "position_id",referencedColumnName = "id"))
	public ArtistCreditNamePosition<?> position; 
	
	@Column(name="artist_credit_name_join_phrase" ,nullable=true,insertable=true)
	public String artistCreditNameJoinPhrase;

	public ArtistCreditName(@NotBlank String name, @NotNull Long artistCreditNameId) {
		super(name);
		this.artistCreditNameId = artistCreditNameId;
	}
	
	public ArtistCreditNamePosition<?> getPosition() {
		return position;
	}
	
	public void setPosition(ArtistCreditNamePosition<?> position) {
		this.position = position;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
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
	public Long getArtistCreditNameId() {
		return artistCreditNameId;
	}
	@Override
	public void setArtistCreditNameId(Long k) {
			this.artistCreditNameId = k;
	}

	@Override
	public String getArtistCreditJoinPhrase() {
		return artistCreditNameJoinPhrase;
	}

	@Override
	public void setArtistCreditJoinPhrase(String k) {
		this.artistCreditNameJoinPhrase = k;		
	}
	
}
