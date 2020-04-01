package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Track;
import org.nanotek.entities.BaseTrackBeanBean;
import org.nanotek.proxy.ProxyBase;

public class TrackBean 
<K extends BaseBean<TrackBean<K>,Track<?>>> 
extends ProxyBase<TrackBean<K>,Track<?>>
implements BaseTrackBeanBean<TrackBean<K>>{

	private static final long serialVersionUID = 7327347644746001993L;


	//	
	//	public Long trackId; 
	//	public String gid; 
	//	public Long recordingId; 
	//	public Long medium; 
	//	public Integer position; 
	//	public String number; 
	//	public String name; 
	//	public Long artistCreditId; 
	//	public Long length; 
	//	public Long editsPending; 
	//	public String lastUpdate;
	//	public String isDataTrack;



	public TrackBean(Class<? extends Track<?>> class1) {
		super(class1);
	}



	public TrackBean() {
		super(castClass());
	}


	private static Class<? extends Track<?>> castClass() {
		return (Class<? extends Track<?>>) Track.class.asSubclass(Track.class);
	}



}