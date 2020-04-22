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
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseTrackEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Entity
@Table(name="track",
uniqueConstraints = {@UniqueConstraint(name="uk_track_id",columnNames = {"track_id"})})
public class Track<K extends Track<K>> 
extends BrainzBaseEntity<K>
implements BaseTrackEntity<K>{

	private static final long serialVersionUID = 8642862162010029043L;

	@NotNull(groups = {CsvValidationGroup.class,Default.class,PrePersistValidationGroup.class})
	@Column(name="track_id")
	public Long trackId;
	
	@NotNull(groups = {Default.class,PrePersistValidationGroup.class})
	@ManyToOne(optional=false)
	public Medium<?> medium; 
	
	@NotNull(groups = {Default.class,PrePersistValidationGroup.class})
	@OneToOne(optional=false)
	@JoinTable(
			  name = "track_position_join", 
			  joinColumns = @JoinColumn(name = "track_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "position_id",referencedColumnName = "id"))
	public TrackPosition<?> position;
	
	
	@NotNull(groups = {Default.class,PrePersistValidationGroup.class})
	@OneToOne(optional=false)
	@JoinTable(
			  name = "track_number_join", 
			  joinColumns = @JoinColumn(name = "track_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "number_id",referencedColumnName = "id"))
	public TrackNumber number; 
	
	@NotNull(groups = {Default.class,PrePersistValidationGroup.class})
	@ManyToOne(optional=false)
	public ArtistCredit<?> artistCredit; 
	
	@NotNull(groups = {Default.class,PrePersistValidationGroup.class})
	@OneToOne(optional=false)
	@JoinTable(
			  name = "track_length_join", 
			  joinColumns = @JoinColumn(name = "track_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "length_id",referencedColumnName = "id"))
	public TrackLength length;
	
	@NotNull(groups = {Default.class,PrePersistValidationGroup.class})
	@ManyToOne(fetch=FetchType.LAZY , optional = false)
	@JoinColumn(name="recordingId" , referencedColumnName="id")
	public Recording<?> recording;
	
	public Medium<?> getMedium() {
		return medium;
	}
	public void setMedium(Medium<?> medium) {
		this.medium = medium;
	}
	
	public TrackPosition<?> getPosition() {
		return position;
	}
	public void setPosition(TrackPosition<?> position) {
		this.position = position;
	}
	
	public TrackNumber getNumber() {
		return number;
	}
	public void setNumber(TrackNumber number) {
		this.number = number;
	}
	
	
	public ArtistCredit<?> getArtistCredit() {
		return artistCredit;
	}
	public void setArtistCredit(ArtistCredit<?> artistCredit) {
		this.artistCredit = artistCredit;
	}
	
	public TrackLength getLength() {
		return length;
	}
	public void setLength(TrackLength length) {
		this.length = length;
	}
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
}
