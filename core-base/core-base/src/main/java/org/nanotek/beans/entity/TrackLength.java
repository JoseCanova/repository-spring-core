package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableTrackEntity;

@Entity
@DiscriminatorValue(value="TrackLength")
public class TrackLength extends LongLengthyBase<TrackLength> implements MutableTrackEntity<Track>{
//ImmutableLengthIdBase<TrackLength , Long,Long> 

	private static final long serialVersionUID = 3623778681403832594L;
	
	@NotNull
	@OneToOne(optional=false)
	private Track track;
	
	public TrackLength() {
	}

	public TrackLength(@NotNull Long length) {
		super(length);
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

}
