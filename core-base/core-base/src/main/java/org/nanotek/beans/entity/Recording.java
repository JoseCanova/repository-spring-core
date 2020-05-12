package org.nanotek.beans.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Cacheable;
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
import org.nanotek.entities.BaseRecordingEntity;
import org.nanotek.entities.MutableArtistCreditEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableRecordingCommentEntity;
import org.nanotek.entities.MutableRecordingIdEntity;
import org.nanotek.entities.MutableRecordingIsrcSetEntity;
import org.nanotek.entities.MutableRecordingLengthEntity;
import org.nanotek.entities.MutableRecordingNameEntity;
import org.nanotek.entities.MutableTrackEntitySet;
import org.nanotek.opencsv.CsvValidationGroup;

@Valid
@Indexed
@Entity
@Table(name="recording" ,
indexes= {
@Index(name="idx_recording_id",columnList="recording_id")
},
uniqueConstraints = {@UniqueConstraint(name="uk_recording_id",columnNames = {"recording_id"})
})
public class Recording
<E extends Recording<E>> extends BrainzBaseEntity<E> implements 
MutableRecordingIdEntity<Long> , 
BaseRecordingEntity<E>,
MutableArtistCreditEntity<ArtistCredit<?>>,
MutableTrackEntitySet<Track<?>>,
MutableRecordingLengthEntity<RecordingLength<?>>,
MutableGidEntity<UUID>,
MutableRecordingNameEntity<String>,
MutableRecordingCommentEntity<RecordingComment<?>>,
MutableRecordingIsrcSetEntity<Set<Isrc<?>>>{

	private static final long serialVersionUID = 1795844351898160253L;

	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="recording_id" , nullable=false)
	public Long recordingId;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	public UUID gid;
	
	@Field(name = "name" , index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String recordingName;

	@NotNull(groups = {PrePersistValidationGroup.class})
	@ManyToOne(fetch=FetchType.LAZY,optional = false,cascade = CascadeType.PERSIST)
	@JoinColumn(name="artist_credit_id" , referencedColumnName="id")
	public ArtistCredit<?> artistCredit; 
	
	@OneToMany(mappedBy="recording" , fetch=FetchType.LAZY)
	public Set<Track<?>> tracks;
	
	@OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	public RecordingLength<?> recordingLength;
	
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "recording")
	public Set<Isrc<?>> recordingIsrcs;
	
	@OneToOne(cascade = CascadeType.ALL , optional = true , fetch = FetchType.LAZY)
	@JoinTable(
			  name = "recording_comment_join", 
			  joinColumns = @JoinColumn(name = "recording_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "comment_id",referencedColumnName = "id") )
	public RecordingComment<?> recordingComment;
	
	public Recording() {}
	
	public Recording(@NotNull Long id , UUID gid, @NotNull String name) {
		this.gid = gid; 
		this.recordingName = name;
		this.recordingId = id;
	}
	
	public Recording(@NotNull UUID gid, @NotNull String name) {
		this.gid = gid; 
		this.recordingName = name;
	}

	public Set<Track<?>> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track<?>> tracks) {
		this.tracks = tracks;
	}

	public ArtistCredit<?> getArtistCredit() {
		return artistCredit;
	}

	public void setArtistCredit(ArtistCredit<?> artistCredit) {
		this.artistCredit = artistCredit;
	}


	public RecordingLength<?> getRecordingLength() {
		return recordingLength;
	}

	public void setRecordingLength(RecordingLength<?> recordingLength) {
		this.recordingLength = recordingLength;
	}

	@Override
	@BrainzKey(entityClass = Recording.class, pathName = "recordingId")
	public Long getRecordingId() {
		return recordingId;
	}
	
	@Override
	public void setRecordingId(Long recordingId) {
		this.recordingId = recordingId;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public String getRecordingName() {
		return recordingName;
	}

	public void setRecordingName(String name) {
		this.recordingName = name;
	}

	public RecordingComment<?> getRecordingComment() {
		return recordingComment;
	}

	public void setRecordingComment(RecordingComment<?> recordingComment) {
		this.recordingComment = recordingComment;
	}

	public Set<Isrc<?>> getRecordingIsrcs() {
		return recordingIsrcs;
	}

	public void setRecordingIsrcs(Set<Isrc<?>> recordingIsrc) {
		this.recordingIsrcs = recordingIsrc;
	}
	
}
