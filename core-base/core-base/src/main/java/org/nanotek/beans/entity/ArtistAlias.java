package org.nanotek.beans.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseArtistAliasEntity;
import org.nanotek.entities.MutableAliasIdEntity;
import org.nanotek.entities.MutableArtistAliasBeginDateEntity;
import org.nanotek.entities.MutableArtistAliasEndDateEntity;
import org.nanotek.entities.MutableArtistAliasLocaleEntity;
import org.nanotek.entities.MutableArtistAliasNameEntity;
import org.nanotek.entities.MutableArtistAliasSortNameEntity;
import org.nanotek.entities.MutableArtistAliasTypeEntity;
import org.nanotek.entities.MutableArtistEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Valid

@Entity
@Table(name="artist_alias", 
indexes= {
@Index(name="idx_artist_alias_id",columnList="artist_alias_id")
})
public class ArtistAlias<K extends ArtistAlias<K>>  extends 
BrainzBaseEntity<K>  
implements BaseArtistAliasEntity,
MutableAliasIdEntity<Long>,
MutableArtistAliasSortNameEntity<ArtistAliasSortName<?>>,
MutableArtistEntity<Artist<?>> , 
MutableArtistAliasTypeEntity<ArtistAliasType<?>> , 
MutableArtistAliasLocaleEntity<ArtistAliasLocale<?>>,
MutableArtistAliasBeginDateEntity<ArtistAliasBeginDate<?>>,
MutableArtistAliasEndDateEntity<ArtistAliasEndDate<?>>,
MutableArtistAliasNameEntity<String>{

	private static final long serialVersionUID = -6829974720983757034L;

			
	
	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String artistAliasName;


	@Override
	public String getArtistAliasName() {
		return artistAliasName;
	}

	@Override
	public void setArtistAliasName(String k) {
		this.artistAliasName = k;
	}
	
	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="artist_alias_id"  , nullable = false)
	public Long aliasId; 
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@OneToOne(optional = false , cascade = CascadeType.ALL)
	@JoinTable(
			  name = "artist_alias_sortname_join", 
			  joinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "sort_name_id",referencedColumnName = "id") )
	public ArtistAliasSortName<?> artistAliasSortName;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@ManyToOne(optional = false)
	@JoinTable(
			name = "artist_alias_join", 
			inverseJoinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			joinColumns  = @JoinColumn(name = "artist_id",referencedColumnName = "id") )
	public Artist<?> artist;

	@OneToOne(optional = true , cascade = CascadeType.ALL)
	@JoinTable(
			  name = "artist_alias_locale_join", 
			  joinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "locale_id",referencedColumnName = "id") )
	public ArtistAliasLocale<?> artistAliasLocale;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@ManyToOne(optional = false)
	@JoinTable(
			  name = "artist_alias_type_join", 
			  joinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "alias_type_id",referencedColumnName = "id") )
	public ArtistAliasType<?> artistAliasType;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinTable(
			  name = "artist_alias_begin_date_join", 
			  joinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id") )
	public ArtistAliasBeginDate<?> artistAliasBeginDate;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinTable(
			  name = "artist_alias_end_date_join", 
			  joinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id") )
	public ArtistAliasEndDate<?> artistAliasEndDate;
	
	public ArtistAlias() {}
	
	public ArtistAlias(	@NotNull Long id, 
			@NotNull Artist<?> artist, 
			@NotBlank String name) {
				this.artistAliasName = name;
				this.aliasId = id;
				this.artist = artist;
	}
	
	public ArtistAlias(	@NotNull Long id, 
						@NotNull Artist<?> artist, 
						@NotBlank String name,
						@NotNull ArtistAliasSortName<?> sortName) {
		this.artistAliasName = name;
		this.aliasId = id;
		this.artist = artist;
		this.artistAliasSortName = sortName;
	}

	@BrainzKey(entityClass = ArtistAlias.class, pathName = "aliasId")
	public Long getAliasId() {
		return aliasId;
	}

	public void setAliasId(Long aliasId) {
		this.aliasId = aliasId;
	}

	public ArtistAliasSortName<?> getArtistAliasSortName() {
		return artistAliasSortName;
	}

	public void setArtistAliasSortName(ArtistAliasSortName<?> artistAliasSortName) {
		this.artistAliasSortName = artistAliasSortName;
	}

	public Artist<?> getArtist() {
		return artist;
	}

	public void setArtist(Artist<?> artist) {
		this.artist = artist;
	}

	public ArtistAliasLocale<?> getArtistAliasLocale() {
		return artistAliasLocale;
	}

	public void setArtistAliasLocale(ArtistAliasLocale<?> artistAliasLocale) {
		this.artistAliasLocale = artistAliasLocale;
	}

	public ArtistAliasType<?> getArtistAliasType() {
		return artistAliasType;
	}

	public void setArtistAliasType(ArtistAliasType<?> artistAliasType) {
		this.artistAliasType = artistAliasType;
	}

	public ArtistAliasBeginDate<?> getArtistAliasBeginDate() {
		return artistAliasBeginDate;
	}

	public void setArtistAliasBeginDate(ArtistAliasBeginDate<?> artistAliasBeginDate) {
		this.artistAliasBeginDate = artistAliasBeginDate;
	}

	public ArtistAliasEndDate<?> getArtistAliasEndDate() {
		return artistAliasEndDate;
	}

	public void setArtistAliasEndDate(ArtistAliasEndDate<?> artistAliasEndDate) {
		this.artistAliasEndDate = artistAliasEndDate;
	}

}
