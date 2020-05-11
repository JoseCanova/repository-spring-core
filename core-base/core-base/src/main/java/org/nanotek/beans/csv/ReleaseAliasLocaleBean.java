package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseAliasLocale;
import org.nanotek.entities.BaseReleaseAliasLocaleBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseAliasLocaleBean 
<K extends BaseBean<K,ReleaseAliasLocale<?>>>
extends ProxyBase<K,ReleaseAliasLocale<?>>
implements BaseReleaseAliasLocaleBean<K>{

	private static final long serialVersionUID = -2704664983561986684L;

	public ReleaseAliasLocaleBean() {
		super(castClass());
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseAliasLocale<?>> castClass() {
		return (Class<? extends ReleaseAliasLocale<?>>) ReleaseAliasLocale.class.asSubclass(ReleaseAliasLocale.class);
	}

	public ReleaseAliasLocaleBean(Class<? extends ReleaseAliasLocale<?>> class1) {
		super(class1);
	}

	
}
