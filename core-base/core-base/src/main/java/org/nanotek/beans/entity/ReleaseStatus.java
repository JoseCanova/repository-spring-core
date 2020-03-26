package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseReleaseStatusEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableNameEntity;
import org.nanotek.entities.MutableReleaseEntity;

@Entity
@Table(name="release_status",
uniqueConstraints= {
@UniqueConstraint(name="uk_release_status_id",columnNames={"release_status_id"})
})
public class ReleaseStatus
<K extends ReleaseStatus<K>> extends 
BrainzBaseEntity<K> 
implements BaseReleaseStatusEntity<K>,
MutableReleaseStatusIdEntity<Long>,
MutableReleaseEntity<Release<?>>,
MutableGidEntity<UUID>,MutableNameEntity<String>{

	private static final long serialVersionUID = 4793056857806342212L;
	
	@NotNull
	@Column(name="release_status_id",nullable=false)
	public Long releaseStatusId;
	
	@NotNull
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	protected UUID gid;
	
	@NotNull
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String name;
	
	@NotNull
	@OneToOne(mappedBy = "releaseStatus")
	public Release<?> release;
	
	public ReleaseStatus() {
	}
	
	public ReleaseStatus(@NotNull Long id, 
						 @NotBlank String name, 
						 @NotNull  UUID gid) {
		this.gid = gid; 
		this.name = name;
		this.releaseStatusId = id;
	}

	public Long getReleaseStatusId() {
		return releaseStatusId;
	}

	public void setReleaseStatusId(Long releaseStatusId) {
		this.releaseStatusId = releaseStatusId;
	}

	public Release<?> getRelease() {
		return release;
	}

	public void setRelease(Release<?> release) {
		this.release = release;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
