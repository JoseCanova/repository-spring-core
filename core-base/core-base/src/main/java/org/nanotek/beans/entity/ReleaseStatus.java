package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseReleaseStatusEntity;

@Entity
@Table(name="release_status",
uniqueConstraints= {
@UniqueConstraint(name="uk_release_status_id",columnNames={"release_status_id"})
})
public class ReleaseStatus<K extends ReleaseStatus<K>> extends 
LongIdGidName<K> 
implements BaseReleaseStatusEntity<K>{

	private static final long serialVersionUID = 4793056857806342212L;
	
	@NotNull
	@Column(name="release_status_id",nullable=false)
	private Long releaseStatusId;
	
	public ReleaseStatus() {
	}
	
	public ReleaseStatus(@NotNull Long id, 
						 @NotBlank String name, 
						 @NotBlank  String gid) {
		super(gid,name);
		this.releaseStatusId = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((releaseStatusId == null) ? 0 : releaseStatusId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReleaseStatus other = (ReleaseStatus) obj;
		if (releaseStatusId == null) {
			if (other.releaseStatusId != null)
				return false;
		} else if (!releaseStatusId.equals(other.releaseStatusId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReleaseStatus [releaseStatusId=" + releaseStatusId + ", gid=" + gid + ", name=" + name + ", id=" + id
				+ "]";
	}
}
