package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseStatus;
import org.nanotek.entities.BaseReleaseStatusBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseStatusBean 
<K extends BaseBean<ReleaseStatusBean<K>,ReleaseStatus<?>>> 
extends ProxyBase<ReleaseStatusBean<K>,ReleaseStatus<?>>
implements BaseReleaseStatusBean<ReleaseStatusBean<K>>{

	private static final long serialVersionUID = -1032984444131323024L;
	

	
	
//	public Long releaseStatusId; 
//	public String name; 
//	public Long parent; 
//	public Long childOrder; 
//	public String description; 
//	public String gid; 
//	
//	


	public ReleaseStatusBean(Class<? extends ReleaseStatus<?>> class1) {
		super(class1);
	}




	public ReleaseStatusBean() {
		super(castClass());
	}

	
	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseStatus<?>>castClass() {
		return (Class<? extends ReleaseStatus<?>>) 
				ReleaseStatus.class.
				asSubclass(ReleaseStatus.class);
	}

 
}