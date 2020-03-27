package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseArtistAliasTypeEntity;
import org.nanotek.entities.MutableArtistAliasEntity;

@Entity
@DiscriminatorValue(value="ArtistAliasType")
public class ArtistAliasType<K extends ArtistAliasType<K>> 
extends  BaseType<K> 
implements  
BaseArtistAliasTypeEntity<K>,
MutableArtistAliasEntity<ArtistAlias<?>>{

	private static final long serialVersionUID = 430998067473248669L;
	
	@OneToOne(mappedBy = "artistAliasType" , optional = false)
	public ArtistAlias<?> artistAlias;

	public ArtistAliasType() {
	}
	
	public ArtistAliasType(@NotNull Long typeId) {
		super(typeId);
	}

	public ArtistAliasType(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
	}


	public ArtistAliasType(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
		// TODO Auto-generated constructor stub
	}

	public ArtistAlias<?> getArtistAlias() {
		return artistAlias;
	}

	public void setArtistAlias(ArtistAlias<?> artistAlias) {
		this.artistAlias = artistAlias;
	}


}
