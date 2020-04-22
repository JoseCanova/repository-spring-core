package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Language;
import org.nanotek.entities.BaseLanguageBean;
import org.nanotek.proxy.ProxyBase;

public class LanguageBean
<K extends BaseBean<LanguageBean<K>,Language<?>>> 
extends ProxyBase<LanguageBean<K>,Language<?>>
implements BaseLanguageBean<LanguageBean<K>> {

	public LanguageBean() {
		super(castClass());
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Language<?>> castClass() {
		return (Class<? extends Language<?>>) Language.class.asSubclass(Language.class);
	}
	
	public LanguageBean(Class<? extends Language<?>> class1) {
		super(class1);
	}

	private static final long serialVersionUID = 2997501833949969600L;

}
