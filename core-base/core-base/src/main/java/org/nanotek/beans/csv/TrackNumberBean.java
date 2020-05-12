package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.TrackNumber;
import org.nanotek.entities.BaseTrackNumberBean;
import org.nanotek.proxy.ProxyBase;

public class TrackNumberBean 
<K extends BaseBean<TrackNumberBean<K>,TrackNumber>> 
extends ProxyBase<TrackNumberBean<K>,TrackNumber>
implements BaseTrackNumberBean<TrackNumberBean<K>>
{

	private static final long serialVersionUID = 6973560327972367519L;

	public TrackNumberBean() {
		super(TrackNumber.class);
	}
	
	public TrackNumberBean(Class<? extends TrackNumber> clazz) {
		super(clazz);
	}

}
