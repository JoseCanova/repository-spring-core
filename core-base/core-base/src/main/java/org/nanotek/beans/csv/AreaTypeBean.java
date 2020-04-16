package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.entities.BaseAreaTypeBean;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.proxy.ProxyBase; 

public class AreaTypeBean
<K extends BaseBean<AreaTypeBean<K>,AreaType<?>>> 
extends ProxyBase<AreaTypeBean<K>,AreaType<?>>
implements BaseAreaTypeBean<AreaTypeBean<K>> {

	private static final long serialVersionUID = -6271568961378072618L;

	BaseBaseTypeDescriptionBean<?> baseTypeDescription;

	public AreaTypeBean() {
		super(castClass());
		postConstruct();
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends AreaType<?>> castClass() {
		return (Class<? extends AreaType<?>>) AreaType.class.asSubclass(AreaType.class);
	}

	private void postConstruct() {
		baseTypeDescription = new  BaseTypeDescriptionBean<>();
	}

	public AreaTypeBean(Class<AreaType<?>> class1) {
		super(class1);
		postConstruct();
	}

	public BaseBaseTypeDescriptionBean<?> getBaseTypeDescription() {
		return baseTypeDescription;
	}

	public void setBaseTypeDescription(BaseBaseTypeDescriptionBean<?> baseTypeDescription) {
		this.baseTypeDescription = baseTypeDescription;
	}

}
