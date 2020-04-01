package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseAliasType;
import org.nanotek.entities.BaseReleaseAliasTypeBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseAliasTypeBean 
<K extends BaseBean<ReleaseAliasTypeBean<K>,ReleaseAliasType<?>>> 
extends ProxyBase<ReleaseAliasTypeBean<K>,ReleaseAliasType<?>>
implements BaseReleaseAliasTypeBean<ReleaseAliasTypeBean<K>>{

	private static final long serialVersionUID = -6271568961378072618L;

	
//	
//	private Long resultAliasTypeId; 
//	private String name; 
//	private Long parent; 
//	private Long childOrder; 
//	private String description; 
//	private String gid;
	

	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseAliasType<?>> castClass() {
		return (Class<? extends ReleaseAliasType<?>>) 
				ReleaseAliasType.class.asSubclass(ReleaseAliasType.class);
	}


	public ReleaseAliasTypeBean() {
		super(castClass());
	}


	public ReleaseAliasTypeBean(Class<? extends ReleaseAliasType<?>> class1) {
		super(class1);
		// TODO Auto-generated constructor stub
	}




}
