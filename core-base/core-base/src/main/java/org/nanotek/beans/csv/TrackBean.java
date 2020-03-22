package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.Track;

public class TrackBean 
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{

	private static final long serialVersionUID = 7327347644746001993L;
	
	private ID id; 
	
	public ID getId() { 
		return id; 
	}
	
	public Long trackId; 
	public String gid; 
	public Long recordingId; 
	public Long medium; 
	public Integer position; 
	public String number; 
	public String name; 
	public Long artistCreditId; 
	public Long length; 
	public Long editsPending; 
	public String lastUpdate;
	public String isDataTrack;

	public TrackBean(Class<ID> id) {
		super(id);
	}

	public TrackBean() {
		super(Track.class);
	}
	
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public Long getRecordingId() {
		return recordingId;
	}
	public void setRecordingId(Long recordingId) {
		this.recordingId = recordingId;
	}
	public Long getMedium() {
		return medium;
	}
	public void setMedium(Long medium) {
		this.medium = medium;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getArtistCreditId() {
		return artistCreditId;
	}
	public void setArtistCreditId(Long artistCreditId) {
		this.artistCreditId = artistCreditId;
	}
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	public Long getEditsPending() {
		return editsPending;
	}
	public void setEditsPending(Long editsPending) {
		this.editsPending = editsPending;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getIsDataTrack() {
		return isDataTrack;
	}
	public void setIsDataTrack(String isDataTrack) {
		this.isDataTrack = isDataTrack;
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	} 
	
}
