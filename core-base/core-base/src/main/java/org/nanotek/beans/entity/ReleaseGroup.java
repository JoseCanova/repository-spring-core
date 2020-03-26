package org.nanotek.beans.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseReleaseGroupEntity;
import org.nanotek.entities.MutableArtistCreditEntity;
import org.nanotek.entities.MutableReleaseGroupIdEntity;
import org.nanotek.entities.MutableReleaseGroupPrimaryTypeEntity;
import org.nanotek.entities.MutableReleaseSetEntity;

@Entity
@Table(name="release_group",
uniqueConstraints= {
@UniqueConstraint(name="uk_release_group_id",columnNames={"release_group_id"})
})
public class ReleaseGroup
<E extends ReleaseGroup<E>> extends BrainzBaseEntity<E> 
implements BaseReleaseGroupEntity<E>, 
MutableReleaseGroupIdEntity<Long>,
MutableArtistCreditEntity<ArtistCredit<?>>,
MutableReleaseGroupPrimaryTypeEntity<ReleaseGroupPrimaryType<?>>,
MutableReleaseSetEntity<Release<?>>{

	private static final long serialVersionUID = 7603390865547084527L;
	
	@NotNull
	@Column(name="release_group_id" , nullable=false)
	public Long releaseGroupId;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY , optional = false )
	@JoinColumn(name="artist_credit_id")
	public ArtistCredit<?> artistCredit; 
	
	@ManyToOne(fetch = FetchType.LAZY , optional = true)
	@JoinColumn(name = "type_id")
	public ReleaseGroupPrimaryType<?> releaseGroupPrimaryType;
	
	
	@OneToMany(mappedBy = "releaseGroup")
	public Set<Release<?>> releases;
	
	public ReleaseGroup() {}
	
	public ReleaseGroup(@NotNull Long id, @NotBlank String gid, @NotBlank String name, @NotNull ArtistCredit<?> artistCredit,
			ReleaseGroupPrimaryType<?> type) {
		super(gid,name);
		this.artistCredit = artistCredit;
		this.releaseGroupPrimaryType = type;
		this.releaseGroupId = id;
	}

	public Long getReleaseGroupId() {
		return releaseGroupId;
	}

	public void setReleaseGroupId(Long releaseGroupId) {
		this.releaseGroupId = releaseGroupId;
	}

	public ArtistCredit<?> getArtistCredit() {
		return artistCredit;
	}

	public void setArtistCredit(ArtistCredit<?> artistCredit) {
		this.artistCredit = artistCredit;
	}

	public ReleaseGroupPrimaryType<?> getReleaseGroupPrimaryType() {
		return releaseGroupPrimaryType;
	}

	public void setReleaseGroupPrimaryType(ReleaseGroupPrimaryType<?> releaseGroupPrimaryType) {
		this.releaseGroupPrimaryType = releaseGroupPrimaryType;
	}

	public Set<Release<?>> getReleases() {
		return releases;
	}

	public void setReleases(Set<Release<?>> releases) {
		this.releases = releases;
	}


}
