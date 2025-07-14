package org.nanotek.beans.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseArtistEntity;
import org.nanotek.entities.MutableAreaEntity;
import org.nanotek.entities.MutableArtistBeginAreaEntity;
import org.nanotek.entities.MutableArtistBeginDateEntity;
import org.nanotek.entities.MutableArtistCommentEntity;
import org.nanotek.entities.MutableArtistCreditCollection;
import org.nanotek.entities.MutableArtistEndAreaEntity;
import org.nanotek.entities.MutableArtistEndDateEntity;
import org.nanotek.entities.MutableArtistIdEntity;
import org.nanotek.entities.MutableArtistNameEntity;
import org.nanotek.entities.MutableArtistSortNameEntity;
import org.nanotek.entities.MutableArtistTypeEntity;
import org.nanotek.entities.MutableGenderEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Valid

@Entity
@Table(name="artist" , 
indexes= {
		@Index(name="idex_artist_id",columnList="artist_id")
})
public class Artist
<K extends Artist<K>> extends 
BrainzBaseEntity<K> implements BaseArtistEntity<K>,
MutableArtistIdEntity<Long>,
MutableArtistSortNameEntity<ArtistSortName<?>>,
MutableArtistCommentEntity<ArtistComment<?>>,
MutableArtistBeginDateEntity<ArtistBeginDate<?>>,
MutableArtistEndDateEntity<ArtistEndDate<?>>,
MutableArtistTypeEntity<ArtistType<?>>,
MutableGenderEntity<Gender<?>>,
MutableAreaEntity<Area<?>>,
MutableArtistBeginAreaEntity<Area<?>>,
MutableArtistEndAreaEntity<Area<?>>,
MutableGidEntity<UUID>,MutableArtistNameEntity<String>,
MutableArtistCreditCollection<List<ArtistCredit<?>>>
{

	private static final long serialVersionUID = -932806802235346847L;

	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="artist_id" , nullable = false , insertable = true , updatable = false)
	public Long artistId;


	@NotNull(groups= {PrePersistValidationGroup.class})
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	public UUID gid;


	public void setGid(UUID gid) {
		this.gid = gid;
	}


	public UUID getGid() {
		return gid;
	}	


	
	@NotBlank(groups= {PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String artistName;


	@Override
	public String getArtistName() {
		return artistName;
	}

	@Override
	public void setArtistName(String k) {
		this.artistName = k;
	}


	@ManyToMany(mappedBy = "artists",fetch=FetchType.LAZY , targetEntity=org.nanotek.beans.entity.ArtistCredit.class)
	public List<ArtistCredit<?>> artistCredits;

	@NotNull(groups= {PrePersistValidationGroup.class})
	@OneToOne(optional=false , cascade = CascadeType.ALL)
	@JoinTable(
			name = "artist_sortname_join", 
			joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "sort_name_id",referencedColumnName = "id") )
	public ArtistSortName<?> artistSortName;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(
			name = "artist_comment_join", 
			joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "comment_id",referencedColumnName = "id") )
	public ArtistComment<?> artistComment;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(
			name = "artist_begin_date_join", 
			joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id") )
	public ArtistBeginDate<?> artistBeginDate; 

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(
			name = "artist_end_date_join", 
			joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id"))
	public ArtistEndDate<?> artistEndDate;

	@NotNull(groups= {PrePersistValidationGroup.class})
	@ManyToOne(optional = false)
	public ArtistType<?> artistType; 

	@NotNull(groups= {PrePersistValidationGroup.class})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(
			name = "artist_gender_join", 
			joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "gender_id",referencedColumnName = "id"))
	public Gender<?> gender; 

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(
			name = "artist_area_join", 
			joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "area_id",referencedColumnName = "id"))
	public Area<?> area;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(
			name = "artist_begin_area_join", 
			joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "area_id",referencedColumnName = "id"))
	public Area<?> beginArea;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(
			name = "artist_end_area_join", 
			joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "area_id",referencedColumnName = "id"))
	public Area<?> endArea;


	@OneToMany(mappedBy = "artist")
	public List<ArtistAlias<?>> artistAlias;

	public Artist() {
//		prepare();
	}

	public Artist(
			@NotBlank Long id, 
			@NotBlank String name, 
			@NotBlank UUID gid) {
		this.gid  =  gid;
		this.artistName =name;
		this.artistId = id;
		artistCredits = new ArrayList<ArtistCredit<?>>();
	}

	@Override
	public void setArtistId(Long k) {
		this.artistId = k;
	}

	@BrainzKey(entityClass = Artist.class, pathName = "artistId")
	@Override
	public Long getArtistId() {
		return artistId;
	}

	@Override
	public void setArtistSortName(ArtistSortName<?> k) {
		this.artistSortName = k;
	}

	@Override
	public ArtistSortName<?> getArtistSortName() {
		return this.artistSortName;
	}

	@Override
	public void setArtistComment(ArtistComment<?> k) {
		this.artistComment = k;
	}

	@Override
	public ArtistComment<?> getArtistComment() {
		return this.artistComment;
	}

	@Override
	public void setArtistBeginDate(ArtistBeginDate<?> k) {
		this.artistBeginDate = k;
	}

	@Override
	public ArtistBeginDate<?> getArtistBeginDate() {
		return this.artistBeginDate;
	}

	@Override
	public void setArtistEndDate(ArtistEndDate<?> k) {
		this.artistEndDate = k;
	}


	@Override
	public void setArtistType(ArtistType<?> k) {
		this.artistType = k;
	}

	@Override
	public ArtistType<?> getArtistType() {
		return this.artistType;
	}

	public List<ArtistCredit<?>> getArtistCredits() {
		return artistCredits;
	}

	public void setArtistCredits(List<ArtistCredit<?>> artistCredits) {
		this.artistCredits = artistCredits;
	}

	public Area<?> getBeginArea() {
		return beginArea;
	}

	public void setBeginArea(Area<?> beginArea) {
		this.beginArea = beginArea;
	}

	public Area<?> getEndArea() {
		return endArea;
	}

	public void setEndArea(Area<?> endArea) {
		this.endArea = endArea;
	}

	public ArtistEndDate<?> getArtistEndDate() {
		return artistEndDate;
	}

	@Override
	public void setGender(Gender<?> k) {
		this.gender = k;
	}

	@Override
	public Gender<?> getGender() {
		return this.gender;
	}

	@Override
	public void setArea(Area<?> k) {
		this.area = k;
	}

	@Override
	public Area<?> getArea() {
		return this.area;
	}

	@Override
	public String toString() {
		return withUUID().toString();
	}

}
