package org.nanotek.beans.entity;

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
import org.nanotek.entities.MutableReleaseIdEntity;


@Entity
@Table(name="release",
uniqueConstraints= {
@UniqueConstraint(name="uk_release_id",columnNames={"release_id"})
})
public class Release<K extends Release<K>> extends LongIdGidName<K> implements 
BaseReleaseEntity<K>,
MutableReleaseIdEntity<Long> {

	private static final long serialVersionUID = 8526436903189806951L;
		
	@Column(name="release_id" , nullable=false)
	private Long releaseId;

	@OneToOne(optional = true , orphanRemoval = true)
	@JoinTable(
			  name = "release_barcode_join", 
			  joinColumns = @JoinColumn(name = "release_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "barcode_id",referencedColumnName = "id"))
	private ReleaseBarCode barCode;
	
	@OneToOne(optional = true , orphanRemoval = true)
	@JoinTable(
			  name = "release_comment_join", 
			  joinColumns = @JoinColumn(name = "release_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "barcode_id",referencedColumnName = "id"))
	private ReleaseComment comment; 

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	private ReleaseStatus status; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="packaging_id")
	private ReleasePackaging packaging;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="language_id")
	private Language language; 

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="release_group_id" , insertable=true , nullable=false)
	private ReleaseGroup releaseGroup; 

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="artist_credit_id" , referencedColumnName="id")
	private ArtistCredit artistCredit;

	public Release() { 
	}
	
	public Release(@NotNull Long id, @NotBlank @Size(min = 1, max = 50) String gid, @NotBlank String name) {
		super(gid, name);
		this.releaseId = id;
	}

	public Release(
			@NotNull Long id, 
			@NotBlank @NotBlank @Size(min = 1, max = 50) String gid, 
			@NotBlank  String name, 
			ReleaseBarCode barCode,
			ReleaseComment comment, 
			ReleaseStatus status, 
			ReleasePackaging packaging, 
			Language language,
			ReleaseGroup releaseGroup, 
			ArtistCredit artistCredit) {
		this.releaseId = id;
		this.gid = gid;
		this.name = name;
		this.barCode = barCode;
		this.comment = comment;
		this.status = status;
		this.packaging = packaging;
		this.language = language;
		this.releaseGroup = releaseGroup;
		this.artistCredit = artistCredit;
	}


	public ReleaseBarCode getBarCode() {
		return barCode;
	}

	public void setBarCode(ReleaseBarCode barCode) {
		this.barCode = barCode;
	}

	public ReleaseComment getComment() {
		return comment;
	}

	public void setComment(ReleaseComment comment) {
		this.comment = comment;
	}

	public ReleaseStatus getStatus() {
		return status;
	}

	public void setStatus(ReleaseStatus status) {
		this.status = status;
	}

	public ReleasePackaging getPackaging() {
		return packaging;
	}

	public void setPackaging(ReleasePackaging packaging) {
		this.packaging = packaging;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public ReleaseGroup getReleaseGroup() {
		return releaseGroup;
	}

	public void setReleaseGroup(ReleaseGroup releaseGroup) {
		this.releaseGroup = releaseGroup;
	}

	public ArtistCredit getArtistCredit() {
		return artistCredit;
	}

	public void setArtistCredit(ArtistCredit artistCredit) {
		this.artistCredit = artistCredit;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((releaseId == null) ? 0 : releaseId.hashCode());
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
		Release other = (Release) obj;
		if (releaseId == null) {
			if (other.releaseId != null)
				return false;
		} else if (!releaseId.equals(other.releaseId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Release [releaseId=" + releaseId + ", barCode=" + barCode + ", comment=" + comment + ", status="
				+ status + ", packaging=" + packaging + ", language=" + language + ", releaseGroup=" + releaseGroup
				+ ", artistCredit=" + artistCredit + ", gid=" + gid + ", name=" + name + ", id=" + id + "]";
	}

	public Long getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(Long releaseId) {
		this.releaseId = releaseId;
	}
	
	
}
