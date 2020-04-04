package org.nanotek.beans.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseArtistEntity;

@Entity
@Table(name="artist" , 
		uniqueConstraints= {
		@UniqueConstraint(name="uk_artist_id",columnNames={"artist_id"})
		})
public class Artist
<K extends Artist<K>> extends 
BrainzBaseEntity<K> implements BaseArtistEntity<K>
{
	
	private static final long serialVersionUID = -932806802235346847L;

	@Column(name="artist_id" , nullable = false , insertable = true , updatable = false)
	public Long artistId;
	
	@NotNull
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	public UUID gid;
	
	
	public void setGid(UUID gid) {
		this.gid = gid;
	}
	
	
	public UUID getGid() {
		return gid;
	}	

	
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
	
	
	@ManyToMany(mappedBy = "artists",fetch=FetchType.LAZY , targetEntity=org.nanotek.beans.entity.ArtistCredit.class)
	public List<ArtistCredit<?>> artistCredits;

	@NotNull
	@OneToOne(optional=false)
	@JoinTable(
			  name = "artist_sortname_join", 
			  joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "sort_name_id",referencedColumnName = "id") )
	public ArtistSortName<?> artistSortName;
	
	
	@OneToOne
	@JoinTable(
			  name = "artist_comment_join", 
			  joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "comment_id",referencedColumnName = "id") )
	public ArtistComment<?> artistComment;
	
	@OneToOne
	@JoinTable(
			  name = "artist_begin_date_join", 
			  joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id") )
	public ArtistBeginDate<?> artistBeginDate; 
	
	@OneToOne
	@JoinTable(
			  name = "artist_end_date_join", 
			  joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id"))
	public ArtistEndDate<?> artistEndDate;
	
	@NotNull
	@ManyToOne(optional = false)
	public ArtistType<?> artistType; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "artist_gender_join", 
			  joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "gender_id",referencedColumnName = "id"))
	public Gender<?> gender; 

	@ManyToOne
	@JoinTable(
			  name = "artist_area_join", 
			  joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "area_id",referencedColumnName = "id"))
	public Area<?> area;

	
	@ManyToOne
	@JoinTable(
			  name = "artist_begin_area_join", 
			  joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "area_id",referencedColumnName = "id"))
	public Area<?> beginArea;
	
	@ManyToOne
	@JoinTable(
			  name = "artist_end_area_join", 
			  joinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "area_id",referencedColumnName = "id"))
	public Area<?> endArea;
	
	public Artist() {
		prepare();
		
	}
	
	private void prepare() { 
		artistSortName = new ArtistSortName<>(this);
		artistComment = new ArtistComment<>(this);
		artistBeginDate = new ArtistBeginDate<>();
		artistEndDate = new ArtistEndDate<>();
		artistType = new ArtistType<>(this);
		gender = new Gender<>();
		area = new Area<>();
		artistCredits = new ArrayList<ArtistCredit<?>>();
		beginArea = new Area<>();
		endArea=new Area<>();
	}
	
	public Artist(
			@NotBlank Long id, 
			@NotBlank String name, 
			@NotBlank UUID gid) {
		this.gid  =  gid;
		this.name =name;
		this.artistId = id;
		artistCredits = new ArrayList<ArtistCredit<?>>();
	}

	@Override
	public void setArtistId(Long k) {
		this.artistId = k;
	}

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
