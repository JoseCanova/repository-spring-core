package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseArtistAliasTypeEntity;

@Entity
@DiscriminatorValue(value="ArtistAliasType")
public class ArtistAliasType<K extends ArtistAliasType<K>> 
extends  BaseType<K> 
implements  
BaseArtistAliasTypeEntity<K>{

	private static final long serialVersionUID = 430998067473248669L;
	

	public ArtistAliasType() {
		super();
	}
	
	public ArtistAliasType(@NotNull Long typeId) {
		super(typeId);
	}

	public ArtistAliasType(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
	}


	public ArtistAliasType(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
	}


}
