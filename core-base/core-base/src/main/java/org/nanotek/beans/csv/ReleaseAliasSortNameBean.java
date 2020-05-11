package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistAliasSortName;
import org.nanotek.beans.entity.ReleaseAliasSortName;
import org.nanotek.entities.BaseReleaseAliasSortNameBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseAliasSortNameBean 
<K extends BaseBean<ReleaseAliasSortNameBean<K>,ReleaseAliasSortName<?>>> 
extends ProxyBase<ReleaseAliasSortNameBean<K>,ReleaseAliasSortName<?>>
implements BaseReleaseAliasSortNameBean<ReleaseAliasSortNameBean<K>>{

	private static final long serialVersionUID = -8288974192757187546L;

	public ReleaseAliasSortNameBean() {
		super(castClass() );
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseAliasSortName<?>>castClass() {
		return (Class<? extends ReleaseAliasSortName<?>>) 
				ReleaseAliasSortName.class.
				asSubclass(ReleaseAliasSortName.class);
	}
	
	public ReleaseAliasSortNameBean(Class<ReleaseAliasSortName<?>> clazz) {
		super(clazz);
	}
}
