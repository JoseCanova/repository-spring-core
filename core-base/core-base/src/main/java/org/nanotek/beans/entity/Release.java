package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.nanotek.entities.BaseReleaseEntity;
import org.nanotek.entities.MutableArtistCreditEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableLanguageEntity;
import org.nanotek.entities.MutableNameEntity;
import org.nanotek.entities.MutableReleaseIdEntity;
import org.nanotek.entities.MutableReleasePackagingEntity;
import org.nanotek.entities.MutableReleaseBarCodeEntity;
import org.nanotek.entities.MutableReleaseCommentEntity;
import org.nanotek.entities.MutableReleaseGroupEntity;


@Entity
@Table(name="release",
uniqueConstraints= {
@UniqueConstraint(name="uk_release_id",columnNames={"release_id"})
})
public class Release
<K extends Release<K>> extends BrainzBaseEntity<K> implements 
BaseReleaseEntity<K>,
MutableReleaseIdEntity<Long>,
MutableReleaseBarCodeEntity<ReleaseBarCode<?>>,
MutableReleaseCommentEntity<ReleaseComment<?>>,
MutableReleasePackagingEntity<ReleasePackaging<?>>,
MutableLanguageEntity<Language<?>>,
MutableReleaseGroupEntity<ReleaseGroup<?>>,
MutableArtistCreditEntity<ArtistCredit<?>>,
MutableGidEntity<UUID>,MutableNameEntity<String>{

	private static final long serialVersionUID = 8526436903189806951L;
		
	@Column(name="release_id" , nullable=false)
	private Long releaseId;

	@NotNull
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String name;

	@NotNull
	@Column(name="gid", nullable=false , columnDefinition = "VARCHAR(50) NOT NULL")
	protected UUID gid;
	

	
	@OneToOne(optional = true , orphanRemoval = true)
	@JoinTable(
			  name = "release_barcode_join", 
			  joinColumns = @JoinColumn(name = "release_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "barcode_id",referencedColumnName = "id"))
	private ReleaseBarCode<?> releaseBarCode;
	
	@OneToOne(optional = true , orphanRemoval = true)
	@JoinTable(
			  name = "release_comment_join", 
			  joinColumns = @JoinColumn(name = "release_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "barcode_id",referencedColumnName = "id"))
	private ReleaseComment<?> releaseComment; 

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	private ReleaseStatus<?> releaseStatus; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="packaging_id")
	private ReleasePackaging<?> releasePackaging;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="language_id")
	private Language<?> language; 

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="release_group_id" , referencedColumnName="id", insertable=true , nullable=false)
	private ReleaseGroup<?> releaseGroup; 

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="artist_credit_id" , referencedColumnName="id",insertable=true , nullable=true)
	private ArtistCredit<?> artistCredit;

	public Release() { 
	}
	
	public Release(@NotNull Long id, @NotNull UUID gid, @NotBlank String name) {
		this.gid = gid;
		this.releaseId = id;
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
		this.name = name;
		this.releaseBarCode = barCode;
		this.releaseComment = comment;
		this.releaseStatus = status;
		this.releasePackaging = packaging;
		this.language = language;
		this.releaseGroup = releaseGroup;
		this.artistCredit = artistCredit;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}


	
}
