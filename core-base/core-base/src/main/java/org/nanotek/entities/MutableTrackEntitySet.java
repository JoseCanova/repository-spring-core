package org.nanotek.entities;

import java.io.Serializable;
import java.util.Set;

import org.nanotek.entities.immutables.TrackEntitySet;

public interface MutableTrackEntitySet<T extends Serializable>  extends TrackEntitySet<T>{

	void setTracks(Set<T> t);
	
}
