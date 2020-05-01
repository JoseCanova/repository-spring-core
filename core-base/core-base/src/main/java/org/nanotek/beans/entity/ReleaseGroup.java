package org.nanotek.beans.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseReleaseGroupEntity;
import org.nanotek.entities.MutableArtistCreditEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableReleaseGroupCommentEntity;
import org.nanotek.entities.MutableReleaseGroupIdEntity;
import org.nanotek.entities.MutableReleaseGroupNameEntity;
import org.nanotek.entities.MutableReleaseGroupPrimaryTypeEntity;
import org.nanotek.entities.MutableReleaseSetEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Valid
@Indexed
@Entity
@Table(name="release_group",
indexes= {
@Index(name="idx_release_group_id",columnList="release_group_id")
},
uniqueConstraints = 
{@UniqueConstraint(name="uk_release_group_id",columnNames = {"release_group_id"})})
public class ReleaseGroup
<E extends ReleaseGroup<E>> extends BrainzBaseEntity<E> 
implements BaseReleaseGroupEntity<E>, 
MutableReleaseGroupIdEntity<Long>,
MutableArtistCreditEntity<ArtistCredit<?>>,
MutableReleaseGroupPrimaryTypeEntity<ReleaseGroupPrimaryType<?>>,
MutableReleaseSetEntity<Release<?>>,
MutableGidEntity<UUID>,
MutableReleaseGroupNameEntity<String>,
MutableReleaseGroupCommentEntity<ReleaseGroupComment<?>>{

	private static final long serialVersionUID = 7603390865547084527L;
	
	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="release_group_id" , nullable=false)
	public Long releaseGroupId;

	@NotNull(groups = {PrePersistValidationGroup.class})
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	public UUID gid;
	
	@Field(name = "name" , index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String releaseGroupName;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@ManyToOne(fetch = FetchType.LAZY , optional = false )
	@JoinColumn(name="artist_credit_id")
	public ArtistCredit<?> artistCredit; 
	
	@ManyToOne(fetch = FetchType.LAZY , optional = true)
	@JoinColumn(name = "type_id")
	public ReleaseGroupPrimaryType<?> releaseGroupPrimaryType;
	
	@OneToMany(mappedBy = "releaseGroup")
	public Set<Release<?>> releases;
	
	@OneToOne(cascade = CascadeType.ALL , optional = true , fetch = FetchType.LAZY)
	@JoinTable(
			  name = "release_group_comment_join", 
			  joinColumns = @JoinColumn(name = "release_group_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "comment_id",referencedColumnName = "id") )
	public ReleaseGroupComment<?> releaseGroupComment;
	
	public ReleaseGroup() {}
	
	
	public ReleaseGroup(@NotNull Long releaseGroupId, @NotNull UUID gid, @NotNull String releaseGroupName) {
		super();
		this.releaseGroupId = releaseGroupId;
		this.gid = gid;
		this.releaseGroupName = releaseGroupName;
	}



	public ReleaseGroup(@NotNull Long id, @NotNull UUID gid, @NotBlank String name, @NotNull ArtistCredit<?> artistCredit,
			ReleaseGroupPrimaryType<?> type) {
		this.gid = gid; 
		this.releaseGroupName = name;
		this.artistCredit = artistCredit;
		this.releaseGroupPrimaryType = type;
		this.releaseGroupId = id;
	}

	@BrainzKey(entityClass = ReleaseGroup.class, pathName = "releaseGroupId")
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

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public String getReleaseGroupName() {
		return releaseGroupName;
	}

	public void setReleaseGroupName(String name) {
		this.releaseGroupName = name;
	}


	public ReleaseGroupComment<?> getReleaseGroupComment() {
		return releaseGroupComment;
	}


	public void setReleaseGroupComment(ReleaseGroupComment<?> releaseGroupComment) {
		this.releaseGroupComment = releaseGroupComment;
	}

}
