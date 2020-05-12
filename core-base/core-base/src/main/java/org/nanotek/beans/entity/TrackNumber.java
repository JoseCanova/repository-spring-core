package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableNumberEntity;

@Valid
@Entity
@DiscriminatorValue(value="TrackNumber")
public class TrackNumber 
extends StringNumberBase<TrackNumber>
implements MutableNumberEntity<String>{

	private static final long serialVersionUID = -3716558925729074194L;
	
	@OneToOne(mappedBy = "trackNumber")
	public Track<?> track;
	
	public TrackNumber() {
	}
	
	public TrackNumber(@NotNull String number )
	{ 
		super(number);
	}

	public TrackNumber(@NotNull String number , @NotNull Track<?> track) {
		super(number);
		this.track = track;
	}

	public Track<?> getTrack() {
		return track;
	}

	public void setTrack(Track<?> track) {
		this.track = track;
	}

}
