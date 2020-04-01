package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleasePackaging;
import org.nanotek.entities.BaseReleasePackagingBean;
import org.nanotek.proxy.ProxyBase;

public class ReleasePackagingBean
<K extends BaseBean<ReleasePackagingBean<K>,ReleasePackaging<?>>> 
extends ProxyBase<ReleasePackagingBean<K>,ReleasePackaging<?>>
implements BaseReleasePackagingBean<ReleasePackagingBean<K>>{

	private static final long serialVersionUID = -6068518463159348252L;


	public Long releasePackagingId; 
	public String name; 
	public Long parent; 
	public Long childOrder; 
	public String description; 
	public String gid;
	

	public ReleasePackagingBean(Class<? extends ReleasePackaging<?>> class1) {
		super(class1);
	}


	public ReleasePackagingBean() {
		super((Class<? extends ReleasePackaging<?>>) ReleasePackaging.class);
	}


	
}