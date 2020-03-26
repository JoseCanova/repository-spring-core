package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseReleaseStatusEntity;
import org.nanotek.entities.MutableReleaseEntity;

@Entity
@Table(name="release_status",
uniqueConstraints= {
@UniqueConstraint(name="uk_release_status_id",columnNames={"release_status_id"})
})
public class ReleaseStatus<K extends ReleaseStatus<K>> extends 
LongIdGidName<K> 
implements BaseReleaseStatusEntity<K>,
MutableReleaseStatusIdEntity<Long>,
MutableReleaseEntity<Release<?>>{

	private static final long serialVersionUID = 4793056857806342212L;
	
	@NotNull
	@Column(name="release_status_id",nullable=false)
	public Long releaseStatusId;
	
	@NotNull
	@OneToOne(mappedBy = "releaseStatus")
	public Release<?> release;
	
	public ReleaseStatus() {
	}
	
	public ReleaseStatus(@NotNull Long id, 
						 @NotBlank String name, 
						 @NotBlank  String gid) {
		super(gid,name);
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

}
