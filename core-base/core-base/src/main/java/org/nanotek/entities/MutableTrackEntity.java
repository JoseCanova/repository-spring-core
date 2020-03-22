package org.nanotek.entities;

import java.io.Serializable;

public interface MutableTrackEntity<K extends Serializable> extends TrackEntity<K> {
		void setTrack(K k);
}
