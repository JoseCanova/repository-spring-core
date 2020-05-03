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
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseReleaseStatusEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableReleaseSetEntity;
import org.nanotek.entities.MutableReleaseStatusIdEntity;
import org.nanotek.entities.MutableReleaseStatusNameEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Entity
@Table(name="release_status",
indexes= {
@Index(name="idx_release_status_id",columnList="release_status_id")
},
uniqueConstraints = {
		@UniqueConstraint(name="uk_release_status_id",columnNames = {
				"release_status_id"
		})
})
public class ReleaseStatus
<K extends ReleaseStatus<K>> extends 
BrainzBaseEntity<K> 
implements BaseReleaseStatusEntity<K>,
MutableReleaseStatusIdEntity<Long>,
MutableReleaseSetEntity<Release<?>>,
MutableGidEntity<UUID>,
MutableReleaseStatusNameEntity<String>
{

	private static final long serialVersionUID = 4793056857806342212L;
	
	@NotNull(groups = {CsvValidationGroup.class,Default.class,PrePersistValidationGroup.class})
	@Column(name="release_status_id",nullable=false)
	public Long releaseStatusId;
	
	@NotNull(groups = {Default.class,PrePersistValidationGroup.class})
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	public UUID gid;
	
	@NotNull(groups = {Default.class,PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String releaseStatusName;
	
	@OneToMany(mappedBy = "releaseStatus")
	public Set<Release<?>> releases;
	
	public ReleaseStatus() {
	}
	
	public ReleaseStatus(@NotNull Long id, 
						 @NotBlank String name, 
						 @NotNull  UUID gid) {
		this.gid = gid; 
		this.releaseStatusName = name;
		this.releaseStatusId = id;
	}

	@BrainzKey(entityClass = ReleaseStatus.class, pathName = "releaseStatusId")
	public Long getReleaseStatusId() {
		return releaseStatusId;
	}

	public void setReleaseStatusId(Long releaseStatusId) {
		this.releaseStatusId = releaseStatusId;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public String getReleaseStatusName() {
		return releaseStatusName;
	}

	public void setReleaseStatusName(String name) {
		this.releaseStatusName = name;
	}

	public Set<Release<?>> getReleases() {
		return releases;
	}

	public void setReleases(Set<Release<?>> releases) {
		this.releases = releases;
	}
}
