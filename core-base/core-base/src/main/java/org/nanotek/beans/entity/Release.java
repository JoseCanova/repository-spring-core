package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseReleaseEntity;
import org.nanotek.entities.MutableArtistCreditEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableLanguageEntity;
import org.nanotek.entities.MutableReleaseBarCodeEntity;
import org.nanotek.entities.MutableReleaseCommentEntity;
import org.nanotek.entities.MutableReleaseGroupEntity;
import org.nanotek.entities.MutableReleaseIdEntity;
import org.nanotek.entities.MutableReleaseLabelEntity;
import org.nanotek.entities.MutableReleaseNameEntity;
import org.nanotek.entities.MutableReleasePackagingEntity;
import org.nanotek.entities.MutableReleaseStatusEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Valid
@Indexed
@Entity
@Table(name="release",
indexes= {
@Index(name="idx_release_id",columnList="release_id")
},
uniqueConstraints = {@UniqueConstraint(name="uk_release_id",columnNames = {"release_id"})
})
public class Release
<K extends Release<K>>
extends BrainzBaseEntity<K> 
implements 
BaseReleaseEntity<K>,
MutableReleaseIdEntity<Long>,
MutableReleaseBarCodeEntity<ReleaseBarCode<?>>,
MutableReleaseCommentEntity<ReleaseComment<?>>,
MutableReleasePackagingEntity<ReleasePackaging<?>>,
MutableLanguageEntity<Language<?>>,
MutableReleaseGroupEntity<ReleaseGroup<?>>,
MutableArtistCreditEntity<ArtistCredit<?>>,
MutableGidEntity<UUID>,
MutableReleaseNameEntity<String>,
MutableReleaseLabelEntity<ReleaseLabel<?>>,
MutableReleaseStatusEntity<ReleaseStatus<?>>
{

	private static final long serialVersionUID = 8526436903189806951L;
		
	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="release_id" , nullable=false)
	public Long releaseId;

	@Field(name = "name" , index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String releaseName;

	@NotNull(groups = {PrePersistValidationGroup.class})
	@Column(name="gid", nullable=false , columnDefinition = "VARCHAR(50) NOT NULL")
	public UUID gid;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(
			  name = "release_language_join", 
			  joinColumns = @JoinColumn(name = "release_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "language_id",referencedColumnName = "id"))
	public Language<?> language; 
	
	@ManyToOne(fetch=FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name="artist_credit_id" , referencedColumnName="id",insertable=true , nullable=true)
	public ArtistCredit<?> artistCredit;
	
	@OneToOne(optional = true , orphanRemoval = true ,  cascade = CascadeType.ALL)
	@JoinTable(
			  name = "release_barcode_join", 
			  joinColumns = @JoinColumn(name = "release_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "barcode_id",referencedColumnName = "id"))
	public ReleaseBarCode<?> releaseBarCode;
	
	@OneToOne(optional = true , orphanRemoval = true ,  cascade = CascadeType.ALL)
	@JoinTable(
			  name = "release_comment_join", 
			  joinColumns = @JoinColumn(name = "release_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "barcode_id",referencedColumnName = "id"))
	public ReleaseComment<?> releaseComment; 

	@ManyToOne(fetch=FetchType.LAZY,optional = true ,  cascade = CascadeType.ALL)
	@JoinTable(
			  name = "release_status_join", 
			  joinColumns = @JoinColumn(name = "release_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "status_id",referencedColumnName = "id"))
	public ReleaseStatus<?> releaseStatus; 
	
	@ManyToOne(fetch=FetchType.LAZY,optional = true ,  cascade = CascadeType.ALL)
	@JoinTable(
			  name = "release_packaging_join", 
			  joinColumns = @JoinColumn(name = "release_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "packaging_id",referencedColumnName = "id"))
	public ReleasePackaging<?> releasePackaging;
	
	@ManyToOne(fetch=FetchType.LAZY ,  cascade = CascadeType.ALL)
	@JoinTable(
			  name = "release_group_join", 
			  joinColumns = @JoinColumn(name = "release_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "release_group_id",referencedColumnName = "id"))
	public ReleaseGroup<?> releaseGroup; 

	@OneToOne(optional = true ,  fetch = FetchType.LAZY )
	@JoinTable(
			  name = "release_relabel_join", 
			  joinColumns = @JoinColumn(name = "release_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "release_label_id",referencedColumnName = "id"))
	public ReleaseLabel<?> releaseLabel;

	public Release() { 
	}
	
	public Release(@NotNull(groups = { CsvValidationGroup.class, Default.class }) Long releaseId,
			@NotNull String releaseName, @NotNull UUID gid) {
		super();
		this.releaseId = releaseId;
		this.releaseName = releaseName;
		this.gid = gid;
	}

	public Release(
			@NotNull Long id, 
			@NotBlank @NotNull UUID gid, 
			@NotBlank  String name, 
			ReleaseBarCode<?> barCode,
			ReleaseComment<?> comment, 
			ReleaseStatus<?> status, 
			ReleasePackaging<?> packaging, 
			Language<?> language,
			ReleaseGroup<?> releaseGroup, 
			ArtistCredit<?> artistCredit) {
		this.releaseId = id;
		this.gid = gid;
		this.releaseName = name;
		this.releaseBarCode = barCode;
		this.releaseComment = comment;
		this.releaseStatus = status;
		this.releasePackaging = packaging;
		this.language = language;
		this.releaseGroup = releaseGroup;
		this.artistCredit = artistCredit;
	}

	@BrainzKey(entityClass = Release.class, pathName = "releaseId")
	public Long getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(Long releaseId) {
		this.releaseId = releaseId;
	}

	public ReleaseBarCode<?> getReleaseBarCode() {
		return releaseBarCode;
	}

	public void setReleaseBarCode(ReleaseBarCode<?> releaseBarCode) {
		this.releaseBarCode = releaseBarCode;
	}

	public ReleaseComment<?> getReleaseComment() {
		return releaseComment;
	}

	public void setReleaseComment(ReleaseComment<?> releaseComment) {
		this.releaseComment = releaseComment;
	}

	public ReleaseStatus<?> getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(ReleaseStatus<?> releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public ReleasePackaging<?> getReleasePackaging() {
		return releasePackaging;
	}

	public void setReleasePackaging(ReleasePackaging<?> releasePackaging) {
		this.releasePackaging = releasePackaging;
	}

	public Language<?> getLanguage() {
		return language;
	}

	public void setLanguage(Language<?> language) {
		this.language = language;
	}

	public ReleaseGroup<?> getReleaseGroup() {
		return releaseGroup;
	}

	public void setReleaseGroup(ReleaseGroup<?> releaseGroup) {
		this.releaseGroup = releaseGroup;
	}

	public ArtistCredit<?> getArtistCredit() {
		return artistCredit;
	}

	public void setArtistCredit(ArtistCredit<?> artistCredit) {
		this.artistCredit = artistCredit;
	}

	public String getReleaseName() {
		return releaseName;
	}

	public void setReleaseName(String name) {
		this.releaseName = name;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public ReleaseLabel<?> getReleaseLabel() {
		return releaseLabel;
	}

	public void setReleaseLabel(ReleaseLabel<?> releaseLabel) {
		this.releaseLabel = releaseLabel;
	}
	
}
