package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.ReleasePackagingBase;
import org.nanotek.entities.BaseReleasePackagingEntity;

@Entity
@Table(name="release_packaging",
uniqueConstraints= {
@UniqueConstraint(name="uk_release_pack_id",columnNames={"release_packaging_id"})
})
public class ReleasePackaging<K extends ReleasePackaging<K>> extends LongIdGidName<K> 
implements BaseReleasePackagingEntity<K>,
ReleasePackagingBase<Long>{

	private static final long serialVersionUID = 5351338443793025420L;

	@NotNull
	@Column(name="release_packaging_id" , nullable=false)
	private Long releasePackagingId;
	
	public ReleasePackaging() {}

	public ReleasePackaging(@NotNull Long id, @NotBlank String name, @NotBlank  String gid) {
		super(gid,name);
		this.releasePackagingId = id;
	}

	public Long getReleasePackagingId() {
		return releasePackagingId;
	}

	public void setReleasePackagingId(Long releasePackagingId) {
		this.releasePackagingId = releasePackagingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((releasePackagingId == null) ? 0 : releasePackagingId.hashCode());
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
		ReleasePackaging other = (ReleasePackaging) obj;
		if (releasePackagingId == null) {
			if (other.releasePackagingId != null)
				return false;
		} else if (!releasePackagingId.equals(other.releasePackagingId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReleasePackaging [releasePackagingId=" + releasePackagingId + ", gid=" + gid + ", name=" + name
				+ ", id=" + id + "]";
	}

}
