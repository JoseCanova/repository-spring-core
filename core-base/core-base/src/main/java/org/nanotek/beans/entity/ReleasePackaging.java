package org.nanotek.beans.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.ReleasePackagingBase;
import org.nanotek.entities.BaseReleasePackagingEntity;
import org.nanotek.entities.MutableReleaseEntity;
import org.nanotek.entities.MutableReleasePackagingId;
import org.nanotek.entities.MutableReleaseSetEntity;

@Entity
@Table(name="release_packaging",
uniqueConstraints= {
@UniqueConstraint(name="uk_release_pack_id",columnNames={"release_packaging_id"})
})
public class ReleasePackaging<K extends ReleasePackaging<K>> extends LongIdGidName<K> 
implements BaseReleasePackagingEntity<K>,
ReleasePackagingBase<Long>,
MutableReleasePackagingId<Long>,
MutableReleaseSetEntity<Release<?>>{

	private static final long serialVersionUID = 5351338443793025420L;

	@NotNull
	@Column(name="release_packaging_id" , nullable=false)
	public Long releasePackagingId;
	
	@OneToMany(mappedBy = "releasePackaging")
	public Set<Release<?>> releases;
	
	
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

	public Set<Release<?>> getReleases() {
		return releases;
	}

	public void setReleases(Set<Release<?>> releases) {
		this.releases = releases;
	}


}
