package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
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
import org.nanotek.entities.BaseTrackEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableRecordingEntity;
import org.nanotek.entities.MutableTrackIdEntity;
import org.nanotek.entities.MutableTrackLengthEntity;
import org.nanotek.entities.MutableTrackNameEntity;
import org.nanotek.entities.MutableTrackNumberEntity;
import org.nanotek.entities.MutableTrackPositionEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Valid
@Indexed
@Entity
@Table(name="track",
uniqueConstraints = {@UniqueConstraint(name="uk_track_id",columnNames = {"track_id"})})
public class Track<K extends Track<K>> 
extends BrainzBaseEntity<K>
implements BaseTrackEntity<K>,
MutableTrackIdEntity<Long>, 
//MutableMediumEntity<Medium<?>>,
//MutableArtistCreditEntity<ArtistCredit<?>>,
MutableRecordingEntity<Recording<?>>,
MutableTrackPositionEntity<TrackPosition<?>>,
MutableTrackNumberEntity<TrackNumber>,
MutableTrackLengthEntity<TrackLength>,
MutableGidEntity<UUID>,
MutableTrackNameEntity<String>{

	private static final long serialVersionUID = 8642862162010029043L;

	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="track_id")
	public Long trackId;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@Column(name="gid" , columnDefinition = "UUID NOT NULL")
	public UUID gid; 
	
	
	@Field(name = "name" , index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Column(name="name" , columnDefinition = "VARCHAR NOT NULL")
	public String trackName;
	
	
//	@NotNull(groups = {PrePersistValidationGroup.class})
//	@ManyToOne(optional=false)
//	public Medium<?> medium; 
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(
			  name = "track_position_join", 
			  joinColumns = @JoinColumn(name = "track_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "position_id",referencedColumnName = "id"))
	public TrackPosition<?> trackPosition;
	
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@OneToOne( cascade = CascadeType.ALL)
	@JoinTable(
			  name = "track_number_join", 
			  joinColumns = @JoinColumn(name = "track_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "number_id",referencedColumnName = "id"))
	public TrackNumber trackNumber; 
	
//	@NotNull(groups = {PrePersistValidationGroup.class})
//	@ManyToOne(optional=false)
//	public ArtistCredit<?> artistCredit; 
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(
			  name = "track_length_join", 
			  joinColumns = @JoinColumn(name = "track_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "length_id",referencedColumnName = "id"))
	public TrackLength trackLength;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@ManyToOne(fetch=FetchType.LAZY , optional = false)
	@JoinColumn(name="recordingId" , referencedColumnName="id")
	public Recording<?> recording;
	
//	public Medium<?> getMedium() {
//		return medium;
//	}
//	public void setMedium(Medium<?> medium) {
//		this.medium = medium;
//	}
//	
//	public ArtistCredit<?> getArtistCredit() {
//		return artistCredit;
//	}
//	public void setArtistCredit(ArtistCredit<?> artistCredit) {
//		this.artistCredit = artistCredit;
//	}
	
	@BrainzKey(entityClass = Track.class, pathName = "trackId")
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
	public Recording<?> getRecording() {
		return recording;
	}
	public void setRecording(Recording<?> recording) {
		this.recording = recording;
	}
	public TrackPosition<?> getTrackPosition() {
		return trackPosition;
	}
	public void setTrackPosition(TrackPosition<?> trackPosition) {
		this.trackPosition = trackPosition;
	}
	public TrackNumber getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(TrackNumber trackNumber) {
		this.trackNumber = trackNumber;
	}
	public TrackLength getTrackLength() {
		return trackLength;
	}
	public void setTrackLength(TrackLength trackLength) {
		this.trackLength = trackLength;
	}
	public UUID getGid() {
		return gid;
	}
	public void setGid(UUID gid) {
		this.gid = gid;
	}
	public String getTrackName() {
		return trackName;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	
}
