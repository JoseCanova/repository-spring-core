package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.IdBase;

@SuppressWarnings("serial")
@Entity
@Table(name="track_count")
public class TrackCount implements IdBase<TrackCount,String> {

	private String id; 
	private String trackCount; 
	private String lastUpdate; 
	
	@Override
	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
	}

	
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
