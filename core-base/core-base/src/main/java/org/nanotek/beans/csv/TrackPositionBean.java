package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.TrackPosition;
import org.nanotek.entities.BaseTrackPositionBean;
import org.nanotek.proxy.ProxyBase;

public class TrackPositionBean 
<K extends BaseBean<TrackPositionBean<K>,TrackPosition<?>>> 
extends ProxyBase<TrackPositionBean<K>,TrackPosition<?>>
implements BaseTrackPositionBean<TrackPositionBean<K>>
{

	private static final long serialVersionUID = 1777694925343666102L;

	public TrackPositionBean() {
		super(castClass());
	}
	
	public TrackPositionBean(Class<? extends TrackPosition<?>> class1) {
		super(class1);
	}
	
	private static Class<? extends TrackPosition<?>> castClass() {
		return (Class<? extends TrackPosition<?>>) TrackPosition.class.asSubclass(TrackPosition.class);
	}

}
