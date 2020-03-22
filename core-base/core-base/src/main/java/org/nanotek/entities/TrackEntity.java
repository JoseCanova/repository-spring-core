package org.nanotek.entities;

import java.io.Serializable;

public interface TrackEntity<K extends Serializable> {
	K getTrack();
}
