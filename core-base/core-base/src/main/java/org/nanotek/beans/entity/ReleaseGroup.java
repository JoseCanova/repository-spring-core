package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseReleaseGroupEntity;
import org.nanotek.entities.MutableArtistCreditEntity;
import org.nanotek.entities.MutableReleaseGroupIdEntity;
import org.nanotek.entities.MutableReleaseGroupPrimaryTypeEntity;

@Entity
@Table(name="release_group",
uniqueConstraints= {
@UniqueConstraint(name="uk_release_group_id",columnNames={"release_group_id"})
})
public class ReleaseGroup<E extends ReleaseGroup<E>> extends LongIdGidName<E> 
implements BaseReleaseGroupEntity<E>, 
MutableReleaseGroupIdEntity<Long>,
MutableArtistCreditEntity<ArtistCredit<?>>,
MutableReleaseGroupPrimaryTypeEntity<ReleaseGroupPrimaryType<?>>{

	private static final long serialVersionUID = 7603390865547084527L;
	
	@NotNull
	@Column(name="release_group_id" , nullable=false)
	private Long releaseGroupId;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name="artist_credit_id")
	private ArtistCredit<?> artistCredit; 
	
	@ManyToOne(fetch = FetchType.LAZY , optional = true)
	@JoinColumn(name = "type_id")
	private ReleaseGroupPrimaryType<?> releaseGroupPrimaryType;
	
	public ReleaseGroup() {}
	
	public ReleaseGroup(@NotNull Long id, @NotBlank String gid, @NotBlank String name, @NotNull ArtistCredit<?> artistCredit,
			ReleaseGroupPrimaryType<?> type) {
		super(gid,name);
		this.artistCredit = artistCredit;
		this.releaseGroupPrimaryType = type;
		this.releaseGroupId = id;
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

	public void setReleaseGroupPrimaryType(ReleaseGroupPrimaryType<?> type) {
		this.releaseGroupPrimaryType = type;
	}

	public Long getReleaseGroupId() {
		return releaseGroupId;
	}

	public void setReleaseGroupId(Long releaseGroupId) {
		this.releaseGroupId = releaseGroupId;
	}
	
}
