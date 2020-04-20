package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseAliasType;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.entities.BaseReleaseAliasTypeBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseAliasTypeBean 
<K extends BaseBean<ReleaseAliasTypeBean<K>,ReleaseAliasType<?>>> 
extends ProxyBase<ReleaseAliasTypeBean<K>,ReleaseAliasType<?>>
implements BaseReleaseAliasTypeBean<ReleaseAliasTypeBean<K>>{

	private static final long serialVersionUID = -6271568961378072618L;

	BaseBaseTypeDescriptionBean<?> baseTypeDescription;
	

	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseAliasType<?>> castClass() {
		return (Class<? extends ReleaseAliasType<?>>) 
				ReleaseAliasType.class.asSubclass(ReleaseAliasType.class);
	}


	public ReleaseAliasTypeBean() {
		super(castClass());
		postConstruct();
	}


	public ReleaseAliasTypeBean(Class<? extends ReleaseAliasType<?>> class1) {
		super(class1);
		postConstruct();
	}


	private void postConstruct() {
		baseTypeDescription = new  BaseTypeDescriptionBean<>();
	}


	public BaseBaseTypeDescriptionBean<?> getBaseTypeDescription() {
		return baseTypeDescription;
	}


	public void setBaseTypeDescription(BaseBaseTypeDescriptionBean<?> baseTypeDescription) {
		this.baseTypeDescription = baseTypeDescription;
	}


}
