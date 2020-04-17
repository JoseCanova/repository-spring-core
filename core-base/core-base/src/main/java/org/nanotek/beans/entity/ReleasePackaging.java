package org.nanotek.beans.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.ReleasePackagingBase;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseReleasePackagingEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableReleasePackagingId;
import org.nanotek.entities.MutableReleasePackagingNameEntity;
import org.nanotek.entities.MutableReleaseSetEntity;

@Entity
@Table(name="release_packaging",
indexes= {
@Index(name="idx_release_pack_id",columnList="release_packaging_id")
})
public class ReleasePackaging
<K extends ReleasePackaging<K>> extends BrainzBaseEntity<K> 
implements BaseReleasePackagingEntity<K>,
ReleasePackagingBase<Long>,
MutableReleasePackagingId<Long>,
MutableReleaseSetEntity<Release<?>>,
MutableGidEntity<UUID>,
MutableReleasePackagingNameEntity<String>{

	private static final long serialVersionUID = 5351338443793025420L;

	@NotNull
	@Column(name="release_packaging_id" , nullable=false)
	public Long releasePackagingId;
	
	@NotNull
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	protected UUID gid;
	
	@NotNull
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String releasePackagingName;
	
	@OneToMany(mappedBy = "releasePackaging")
	public Set<Release<?>> releases;
	
	
	public ReleasePackaging() {}

	public ReleasePackaging(@NotNull Long id, @NotBlank String name, @NotNull  UUID gid) {
		this.gid = gid; 
		this.releasePackagingName = name;
		this.releasePackagingId = id;
	}

	@BrainzKey(entityClass = ReleasePackaging.class, pathName = "releasePackagingId")
	public Long getReleasePackagingId() {
		return releasePackagingId;
	}

	public void setReleasePackagingId(Long releasePackagingId) {
		this.releasePackagingId = releasePackagingId;
	}

	public Set<Release<?>> getReleases() {
		return releases;
	}

	public void setReleases(Set<Release<?>> releases) {
		this.releases = releases;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public String getReleasePackagingName() {
		return releasePackagingName;
	}

	public void setReleasePackagingName(String name) {
		this.releasePackagingName = name;
	}


}
