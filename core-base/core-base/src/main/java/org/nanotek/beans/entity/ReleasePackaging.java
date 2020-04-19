package org.nanotek.beans.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.ReleasePackagingBase;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseReleasePackagingEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableReleasePackagingIdEntity;
import org.nanotek.entities.MutableReleasePackagingNameEntity;
import org.nanotek.entities.MutableReleaseSetEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Entity
@Table(name="release_packaging",
indexes= {
@Index(name="idx_release_pack_id",columnList="release_packaging_id")
},
uniqueConstraints = 
{ @UniqueConstraint(name="uk_release_packaging_id",columnNames = {"release_packaging_id"})
		})
public class ReleasePackaging
<K extends ReleasePackaging<K>> extends BrainzBaseEntity<K> 
implements BaseReleasePackagingEntity<K>,
MutableReleasePackagingIdEntity<Long>,
MutableReleaseSetEntity<Release<?>>,
MutableGidEntity<UUID>,
MutableReleasePackagingNameEntity<String>{

	private static final long serialVersionUID = 5351338443793025420L;

	@NotNull(groups = {CsvValidationGroup.class,Default.class,PrePersistValidationGroup.class})
	@Column(name="release_packaging_id" , nullable=false)
	public Long releasePackagingId;
	
	@NotNull(groups = {Default.class,PrePersistValidationGroup.class})
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	public UUID gid;
	
	@NotNull(groups = {Default.class,PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String releasePackagingName;
	
	@Override
	public String toString() {
		return "ReleasePackaging [releasePackagingId=" + releasePackagingId + ", gid=" + gid + ", releasePackagingName="
				+ releasePackagingName + "]";
	}

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
