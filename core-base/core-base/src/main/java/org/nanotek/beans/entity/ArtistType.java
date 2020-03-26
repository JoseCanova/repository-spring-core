package org.nanotek.beans.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@DiscriminatorValue(value="ArtistType")
public class ArtistType<K extends ArtistType<K>> extends BaseType<K> {

	private static final long serialVersionUID = 962190613873549033L;
	
	@OneToMany(mappedBy = "artistType",orphanRemoval = false,fetch = FetchType.LAZY)
	public List<Artist<?>> artists;

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
