package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
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
	
	public ArtistAliasType(@NotBlank @Length(min = 1, max = 50) String gid, @NotBlank String name) {
		super(gid, name);
	}

	public ArtistAliasType(@NotBlank String name) {
		super(name);
	}

	public ArtistAlias<?> getArtistAlias() {
		return artistAlias;
	}

	public void setArtistAlias(ArtistAlias<?> artistAlias) {
		this.artistAlias = artistAlias;
	}


}
