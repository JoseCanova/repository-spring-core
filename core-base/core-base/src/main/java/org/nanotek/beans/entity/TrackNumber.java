package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value="TrackNumber")
public class TrackNumber extends StringNumberBase{

	private static final long serialVersionUID = -3716558925729074194L;
	
	@NotNull
	@OneToOne(optional=false , mappedBy = "number")
	private Track track;
	
	public TrackNumber() {
	}

	public TrackNumber(@NotNull String number , @NotNull Track track) {
		super(number);
		this.track = track;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((track == null) ? 0 : track.hashCode());
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
		TrackNumber other = (TrackNumber) obj;
		if (track == null) {
			if (other.track != null)
				return false;
		} else if (!track.equals(other.track))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrackNumber [track=" + track + ", number=" + number + ", id=" + id + "]";
	}

}
