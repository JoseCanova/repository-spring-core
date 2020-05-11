package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseAliasEndDate;
import org.nanotek.entities.BaseReleaseAliasEndDateBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseAliasEndDateBean
<K extends BaseBean<ReleaseAliasEndDateBean<K>,ReleaseAliasEndDate<?>>> 
extends ProxyBase<ReleaseAliasEndDateBean<K>,ReleaseAliasEndDate<?>>
implements BaseReleaseAliasEndDateBean<ReleaseAliasEndDateBean<K>> {

	
	private static final long serialVersionUID = -3730767802005710713L;

	public ReleaseAliasEndDateBean() {
		super(castClass());
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseAliasEndDate<?>> castClass() {
		return (Class<? extends ReleaseAliasEndDate<?>>) 
				ReleaseAliasEndDate.class.
				asSubclass(ReleaseAliasEndDate.class);
	}
	
	public ReleaseAliasEndDateBean(Class<? extends ReleaseAliasEndDate<?>> class1) {
		super(class1);
	}

}
