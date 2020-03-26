package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.ReleasePackagingBase;
import org.nanotek.entities.BaseReleasePackagingEntity;
import org.nanotek.entities.MutableReleaseEntity;

@Entity
@Table(name="release_packaging",
uniqueConstraints= {
@UniqueConstraint(name="uk_release_pack_id",columnNames={"release_packaging_id"})
})
public class ReleasePackaging<K extends ReleasePackaging<K>> extends LongIdGidName<K> 
implements BaseReleasePackagingEntity<K>,
ReleasePackagingBase<Long>,
MutableReleaseEntity<Release<?>>{

	private static final long serialVersionUID = 5351338443793025420L;

	@NotNull
	@Column(name="release_packaging_id" , nullable=false)
	public Long releasePackagingId;
	
	@OneToOne(mappedBy = "releasePackaging")
	public Release<?> release;
	
	
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

	public Release<?> getRelease() {
		return release;
	}

	public void setRelease(Release<?> release) {
		this.release = release;
	}


}
