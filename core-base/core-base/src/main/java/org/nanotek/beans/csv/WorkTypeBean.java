package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.WorkType;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.entities.BaseWorkTypeBean;
import org.nanotek.proxy.ProxyBase;

public class WorkTypeBean 
<K extends BaseBean<WorkTypeBean<K>,WorkType<?>>> 
extends ProxyBase<WorkTypeBean<K>,WorkType<?>>
implements BaseWorkTypeBean<WorkTypeBean<K>>{
	
	private static final long serialVersionUID = -6809769903803375286L;
	
	BaseBaseTypeDescriptionBean<?> baseTypeDescription;
	
	public WorkTypeBean() {
		super(castClass());
		postConstruct();
	}

	public WorkTypeBean(Class<WorkType<?>> class1) {
		super(class1);
		postConstruct();
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends WorkType<?>> castClass() {
		return (Class<? extends WorkType<?>>) AreaType.class.asSubclass(WorkType.class);
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
