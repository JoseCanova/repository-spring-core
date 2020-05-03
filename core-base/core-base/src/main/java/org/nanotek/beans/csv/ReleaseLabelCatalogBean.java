package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseLabelCatalog;
import org.nanotek.entities.BaseReleaseLabelCatalogBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseLabelCatalogBean 
<K extends BaseBean<ReleaseLabelCatalogBean<K>,ReleaseLabelCatalog<?>>> 
extends ProxyBase<ReleaseLabelCatalogBean<K>,ReleaseLabelCatalog<?>>
implements BaseReleaseLabelCatalogBean<ReleaseLabelCatalogBean<K>>
{

	private static final long serialVersionUID = -3555788767986360344L;

	public ReleaseLabelCatalogBean() {
		super(castClass());
		postConstructReleaseLabelCatalog();
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseLabelCatalog<?>> castClass() {
		return (Class<? extends ReleaseLabelCatalog<?>>) 
				ReleaseLabelCatalog.class.asSubclass(ReleaseLabelCatalog.class);
	}
	
	public ReleaseLabelCatalogBean(Class<ReleaseLabelCatalog<?>>id) {
		super(id);
		postConstructReleaseLabelCatalog();
	}

	private void postConstructReleaseLabelCatalog() {
	}
	
}
