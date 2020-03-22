package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.BaseEntity;
import org.nanotek.entities.BaseArtistAliasEntity;

import com.sun.xml.bind.v2.model.core.ID;

@Entity
@Table(name="artist_alias", 
uniqueConstraints= {
@UniqueConstraint(name="uk_artist_alias_id",columnNames={"artist_alias_id"})
})
public class ArtistAlias<K extends ArtistAlias<K>>  extends 
LongIdName<K>  
implements BaseArtistAliasEntity<K>{

	private static final long serialVersionUID = -6829974720983757034L;

	@NotNull
	@Column(name="artist_alias_id"  , nullable = false)
	public Long aliasId; 
	
	@NotNull
	@OneToOne(optional = false)
	@JoinTable(
			  name = "artist_alias_sortname_join", 
			  joinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "sort_name_id",referencedColumnName = "id") )
	public ArtistAliasSortName<?> artistAliasSortName;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinTable(
			  name = "artist_alias_join", 
			  joinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "artist_id",referencedColumnName = "id") )
	public Artist<?> artist;

	@OneToOne(optional = true)
	@JoinTable(
			  name = "artist_alias_locale_join", 
			  joinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "locale_id",referencedColumnName = "id") )
	public ArtistAliasLocale<?> artistAliasLocale;
	
	@ManyToOne(optional = false)
	@JoinTable(
			  name = "artist_alias_type_join", 
			  joinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "alias_type_id",referencedColumnName = "id") )
	public ArtistAliasType<?,?> artistAliasType;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	@JoinTable(
			  name = "artist_alias_begin_date_join", 
			  joinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id") )
	public ArtistAliasBeginDate artistAliasBeginDate;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	@JoinTable(
			  name = "artist_alias_end_date_join", 
			  joinColumns = @JoinColumn(name = "artist_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id") )
	public ArtistAliasEndDate artistAliasEndDate;
	
	public ArtistAlias() {}
	
	public ArtistAlias(	@NotNull Long id, 
			@NotNull Artist<?> artist, 
			@NotBlank String name) {
				super(name);
				this.aliasId = id;
				this.artist = artist;
	}
	
	public ArtistAlias(	@NotNull Long id, 
						@NotNull Artist<?> artist, 
						@NotBlank String name,
						@NotNull ArtistAliasSortName<?> sortName) {
		super(name);
		this.aliasId = id;
		this.artist = artist;
		this.artistAliasSortName = sortName;
	}

	@Override
	public void setAliasId(Long k) {
		this.aliasId = k;
	}

	@Override
	public Long getAliasId() {
		return this.aliasId;
	}
	
	@Override
	public void setArtistAliasSortName(ArtistAliasSortName<?> k) {
		this.artistAliasSortName = k;
	}

	@Override
	public ArtistAliasSortName<?> getArtistAliasSortName() {
		return this.artistAliasSortName;
	}

	@Override
	public void setArtist(Artist<?> e) {
		this.artist = e;
	}

	@Override
	public Artist<?> getArtist() {
		return this.artist;
	}


	@Override
	public void setArtistAliasLocale(ArtistAliasLocale<?> k) {
		this.artistAliasLocale = k;
	}

	@Override
	public ArtistAliasLocale<?> getArtistAliasLocale() {
		return this.artistAliasLocale;
	}

	@Override
	public void setArtistAliasBeginDate(ArtistAliasBeginDate k) {
		this.artistAliasBeginDate = k;
	}

	@Override
	public ArtistAliasBeginDate getArtistAliasBeginDate() {
		return this.artistAliasBeginDate;
	}

	@Override
	public void setArtistAliasEndDate(ArtistAliasEndDate k) {
		this.artistAliasEndDate = k;
	}

	@Override
	public ArtistAliasEndDate getArtistAliasEndDate() {
		return this.artistAliasEndDate;
	}

	@Override
	public void setArtistAliasType(ArtistAliasType<?, ?> k) {
		this.artistAliasType = k;
		
	}

	@Override
	public ArtistAliasType<?, ?> getArtistAliasType() {
		return artistAliasType;
	}

}
