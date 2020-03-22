package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.nanotek.entities.BaseArtistAliasTypeEntity;
import org.nanotek.entities.MutableArtistAliasEntity;

@Entity
@DiscriminatorValue(value="ArtistAliasType")
public class ArtistAliasType<K extends ArtistAliasType<K,A> , A extends ArtistAlias<A>> 
extends  BaseType<K> 
implements  
BaseArtistAliasTypeEntity<K>,
MutableArtistAliasEntity<A>{

	private static final long serialVersionUID = 430998067473248669L;
	
	@OneToOne(mappedBy = "artistAliasType" , optional = false)
	public A artistAlias;

	public ArtistAliasType() {
	}
	
	public ArtistAliasType(@NotBlank @Length(min = 1, max = 50) String gid, @NotBlank String name) {
		super(gid, name);
	}

	public ArtistAliasType(@NotBlank String name) {
		super(name);
	}

	@Override
	public A getArtistAlias() {
		return artistAlias;
	}

	@Override
	public void setArtistAlias(A k) {
		this.artistAlias = k;
	}

}
