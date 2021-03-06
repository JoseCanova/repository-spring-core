package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@Entity
@DiscriminatorValue(value = "TrackPosition")
public class TrackPosition<K extends TrackPosition<K>>
extends LongPositionBase<K> {

	private static final long serialVersionUID = 6747130680556082235L;
	
	@OneToOne(mappedBy = "trackPosition")
	public Track<?> track;

	public TrackPosition() {
	}

	public TrackPosition(@NotNull Long position) {
		super(position);
	}
	
	public Track<?> getTrack() {
		return track;
	}

	public void setTrack(Track<?> track) {
		this.track = track;
	}
	
}
