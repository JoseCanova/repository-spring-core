package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseTrackEntity;

@Entity
@Table(name="track")
public class Track<K extends Track<K>> extends LongIdGidName<K>
implements BaseTrackEntity<K>{

	private static final long serialVersionUID = 8642862162010029043L;

	@Column(name="track_id")
	private Long trackId;
	
	@ManyToOne(optional=false)
	private Medium medium; 
	
	@OneToOne
	@JoinTable(
			  name = "track_position_join", 
			  joinColumns = @JoinColumn(name = "track_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "position_id",referencedColumnName = "id"))
	private TrackPosition position;
	
	@NotNull
	@OneToOne(optional=false)
	@JoinTable(
			  name = "track_number_join", 
			  joinColumns = @JoinColumn(name = "track_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "number_id",referencedColumnName = "id"))
	private TrackNumber number; 
	
	@NotNull
	@ManyToOne(optional=false)
	private ArtistCredit<?> artistCredit; 
	
	@NotNull
	@OneToOne(optional=false)
	@JoinTable(
			  name = "track_length_join", 
			  joinColumns = @JoinColumn(name = "track_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "length_id",referencedColumnName = "id"))
	private TrackLength length;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY , optional = false)
	@JoinColumn(name="recordingId" , referencedColumnName="id")
	private Recording<?> recording;
	
	public Medium getMedium() {
		return medium;
	}
	public void setMedium(Medium medium) {
		this.medium = medium;
	}
	
	public TrackPosition getPosition() {
		return position;
	}
	public void setPosition(TrackPosition position) {
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
