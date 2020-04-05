package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="track_count")
public class TrackCount<K extends TrackCount<K>> extends BrainzBaseEntity<K> {

	private String trackCount; 
	private String lastUpdate; 
	
	
	public String getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(String trackCount) {
		this.trackCount = trackCount;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	
}
