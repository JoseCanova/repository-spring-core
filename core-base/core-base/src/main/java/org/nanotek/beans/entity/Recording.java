package org.nanotek.beans.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseRecordingEntity;
import org.nanotek.entities.MutableArtistCreditEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableRecordingIdEntity;
import org.nanotek.entities.MutableRecordingLengthEntity;
import org.nanotek.entities.MutableRecordingNameEntity;
import org.nanotek.entities.MutableTrackEntitySet;

@Entity
@Table(name="recording" ,
uniqueConstraints= {
@UniqueConstraint(name="uk_recording_id",columnNames={"recording_id"})
})
public class Recording
<E extends Recording<E>> extends BrainzBaseEntity<E> implements 
MutableRecordingIdEntity<Long> , 
BaseRecordingEntity<E>,
MutableArtistCreditEntity<ArtistCredit<?>>,
MutableTrackEntitySet<Track<?>>,
MutableRecordingLengthEntity<RecordingLength<?>>,
MutableGidEntity<UUID>,MutableRecordingNameEntity<String>{

	private static final long serialVersionUID = 1795844351898160253L;

	@NotNull
	@Column(name="recording_id" , nullable=false)
	public Long recordingId;
	
	@NotNull
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	public UUID gid;
	
	@NotNull
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String recordingName;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY,optional = false)
	@JoinColumn(name="artist_credit_id" , referencedColumnName="id")
	public ArtistCredit<?> artistCredit; 
	
	//TODO: implement proxy for collections.
	@OneToMany(mappedBy="recording" , fetch=FetchType.LAZY)
	private Set<Track<?>> tracks;
	
	@OneToOne(fetch = FetchType.LAZY)
	public RecordingLength<?> recordingLength;
	
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


}
