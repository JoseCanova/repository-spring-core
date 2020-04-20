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