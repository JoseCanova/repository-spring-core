package org.nanotek.beans.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.nanotek.entities.BaseRecordingEntity;
import org.nanotek.entities.MutableRecordingIdEntity;

@Entity
@Table(name="recording" ,
uniqueConstraints= {
@UniqueConstraint(name="uk_recording_id",columnNames={"recording_id"})
})
public class Recording<E extends Recording<E>> extends LongIdGidName<E> implements 
MutableRecordingIdEntity<Long> , BaseRecordingEntity<E>{

	private static final long serialVersionUID = 1795844351898160253L;

	@NotNull
	@Column(name="recording_id" , nullable=false)
	public Long recordingId;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="artist_credit_id" , referencedColumnName="id")
	public ArtistCredit<?> artistCredit; 
	
	@OneToMany(mappedBy="recording" , fetch=FetchType.LAZY)
	public Set<Track> tracks;
	
	@OneToOne(fetch = FetchType.LAZY)
	public RecordingLength recordingLenght;
	
	public Recording() {}
	
	public Recording(@NotNull Long id , @NotBlank @Length(min = 1, max = 50) String gid, @NotNull String name) {
		super(gid, name);
		this.recordingId = id;
	}
	
	public Recording(@NotBlank @Length(min = 1, max = 50) String gid, @NotNull String name) {
		super(gid, name);
	}

	public Set<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	public ArtistCredit getArtistCredit() {
		return artistCredit;
	}

	public void setArtistCredit(ArtistCredit artistCredit) {
		this.artistCredit = artistCredit;
	}

	public RecordingLength getRecordingLenght() {
		return recordingLenght;
	}

	public void setRecordingLenght(RecordingLength recordingLenght) {
		this.recordingLenght = recordingLenght;
	}

	@Override
	public Long getRecordingId() {
		return recordingId;
	}
	
	@Override
	public void setRecordingId(Long recordingId) {
		this.recordingId = recordingId;
	}


}
