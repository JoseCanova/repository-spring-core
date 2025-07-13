package org.nanotek.beans.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.annotations.BrainzKey;

@Entity
@DiscriminatorValue(value="Gender")
public class Gender<K extends Gender<K>> 
extends BaseType<K>{

	private static final long serialVersionUID = 1628032460585953186L;
	
//	@OneToMany
//	@JoinTable(
//			name = "artist_gender_join", 
//			inverseJoinColumns = @JoinColumn(name = "artist_id" , referencedColumnName = "id"), 
//			joinColumns = @JoinColumn(name = "gender_id",referencedColumnName = "id"))
//	public Set<Artist<?>> artists;

	public Gender() {
	}

	public Gender(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
	}

	public Gender(@NotNull Long typeId) {
		super(typeId);
	}

	public Gender(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
	}
	
//	@BrainzKey(entityClass = Artist.class, pathName = "artistId")
//	public Set<Artist<?>> getArtists() {
//		return artists;
//	}
//
//	public void setArtists(Set<Artist<?>> artists) {
//		this.artists = artists;
//	}
}
