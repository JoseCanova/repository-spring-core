package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value="ArtistType")
public class ArtistType<K extends ArtistType<K>> extends BaseType<K> {

	private static final long serialVersionUID = 962190613873549033L;
	

	public ArtistType() {
		super();
	}
	
	public ArtistType(@NotNull Long typeId) {
		super(typeId);
	}

	public ArtistType(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
	}

	public ArtistType(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
	}



}
