package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseGroup;
import org.nanotek.entities.BaseReleaseGroupBean;
import org.nanotek.proxy.ProxyBase;


public class ReleaseGroupBean 
<K extends BaseBean<ReleaseGroupBean<K>,ReleaseGroup<?>>> 
extends ProxyBase<ReleaseGroupBean<K>,ReleaseGroup<?>>
implements BaseReleaseGroupBean<ReleaseGroupBean<K>>{


	private static final long serialVersionUID = -1119657398190391884L;


//	
//	public Long releaseGroupId; 
//	public String gid; 
//	public String name; 
//	public Long artistCredit; 
//	public Long type;
//	public String comment; 
//	public String editsPending;
//	public String lastUpdated;



	public ReleaseGroupBean() { 
		super((Class<? extends ReleaseGroup<?>>) ReleaseGroup.class);
	}


	public ReleaseGroupBean(Class<? extends ReleaseGroup<?>> class1) {
		super(class1);
	}
	

	
}
