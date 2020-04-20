package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.LabelType;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.entities.BaseLabelTypeBean;
import org.nanotek.proxy.ProxyBase;

public class LabelTypeBean 
<K extends BaseBean<LabelTypeBean<K>, LabelType<?>>> 
extends ProxyBase<LabelTypeBean<K>, LabelType<?>>
implements BaseLabelTypeBean<LabelTypeBean<K>> {

	private static final long serialVersionUID = 2469909007956199302L;

	BaseBaseTypeDescriptionBean<?> baseTypeDescription;
	
	public LabelTypeBean() {
		super(castClass());
		postConstruct();
	}
	
	public LabelTypeBean(Class<LabelType<?>> class1) {
		super(class1);
		postConstruct();
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends LabelType<?>> castClass() {
		return (Class<? extends LabelType<?>>) LabelType.class.asSubclass(LabelType.class);
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
