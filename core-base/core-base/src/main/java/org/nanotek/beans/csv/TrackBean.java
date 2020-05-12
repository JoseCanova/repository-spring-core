package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Track;
import org.nanotek.entities.BaseArtistCreditBean;
import org.nanotek.entities.BaseMediumBean;
import org.nanotek.entities.BaseRecordingBean;
import org.nanotek.entities.BaseTrackBean;
import org.nanotek.entities.BaseTrackLengthBean;
import org.nanotek.entities.BaseTrackNumberBean;
import org.nanotek.entities.BaseTrackPositionBean;
import org.nanotek.proxy.ProxyBase;

public class TrackBean 
<K extends BaseBean<TrackBean<K>,Track<?>>> 
extends ProxyBase<TrackBean<K>,Track<?>>
implements BaseTrackBean<TrackBean<K>>{

	private static final long serialVersionUID = 7327347644746001993L;

	BaseMediumBean<?> medium;
	BaseArtistCreditBean<?> artistCredit;
	BaseRecordingBean<?> recording;
	BaseTrackPositionBean<?> trackPosition;
	BaseTrackLengthBean<?> trackLength;
	BaseTrackNumberBean<?> trackNumber;

	public TrackBean(Class<? extends Track<?>> class1) {
		super(class1);
		postConstructTrack();
	}



	private void postConstructTrack() {
		medium = new MediumBean<> ();
		artistCredit = new ArtistCreditBean<>();
		recording = new RecordingBean<> ();
		trackPosition = new TrackPositionBean<> ();
		trackLength = new TrackLengthBean<> ();
		trackNumber = new TrackNumberBean<> ();
	}



	public TrackBean() {
		super(castClass());
		postConstructTrack();
	}

	
	

	private static Class<? extends Track<?>> castClass() {
		return (Class<? extends Track<?>>) Track.class.asSubclass(Track.class);
	}



	public BaseMediumBean<?> getMedium() {
		return medium;
	}



	public void setMedium(BaseMediumBean<?> medium) {
		this.medium = medium;
	}



	public BaseArtistCreditBean<?> getArtistCredit() {
		return artistCredit;
	}



	public void setArtistCredit(BaseArtistCreditBean<?> artistCredit) {
		this.artistCredit = artistCredit;
	}



	public BaseRecordingBean<?> getRecording() {
		return recording;
	}



	public void setRecording(BaseRecordingBean<?> recording) {
		this.recording = recording;
	}



	public BaseTrackPositionBean<?> getTrackPosition() {
		return trackPosition;
	}



	public void setTrackPosition(BaseTrackPositionBean<?> trackPosition) {
		this.trackPosition = trackPosition;
	}



	public BaseTrackLengthBean<?> getTrackLength() {
		return trackLength;
	}



	public void setTrackLength(BaseTrackLengthBean<?> trackLength) {
		this.trackLength = trackLength;
	}



	public BaseTrackNumberBean<?> getTrackNumber() {
		return trackNumber;
	}



	public void setTrackNumber(BaseTrackNumberBean<?> trackNumber) {
		this.trackNumber = trackNumber;
	}



}