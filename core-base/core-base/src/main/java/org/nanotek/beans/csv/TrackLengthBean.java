package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.TrackLength;
import org.nanotek.entities.BaseTrackLengthBean;
import org.nanotek.proxy.ProxyBase;

public class TrackLengthBean 
<K extends BaseBean<TrackLengthBean<K>,TrackLength>> 
extends ProxyBase<TrackLengthBean<K>,TrackLength>
implements BaseTrackLengthBean<TrackLengthBean<K>>
{

	private static final long serialVersionUID = 6773819589180366612L;

	public TrackLengthBean() {
		super(TrackLength.class);
	}
	
	public TrackLengthBean(Class<? extends TrackLength> clazz) {
		super(clazz);
	}

}
