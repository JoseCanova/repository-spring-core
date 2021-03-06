package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseGroupPrimaryType;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.entities.BaseReleaseGroupPrimaryTypeBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseGroupPrimaryTypeBean
<K extends BaseBean<ReleaseGroupPrimaryTypeBean<K>,ReleaseGroupPrimaryType<?>>> 
extends ProxyBase<ReleaseGroupPrimaryTypeBean<K>,ReleaseGroupPrimaryType<?>>
implements BaseReleaseGroupPrimaryTypeBean<ReleaseGroupPrimaryTypeBean<K>>{

	private static final long serialVersionUID = -4792229013963888593L;

	
//	public Long ReleaseGroupPrimaryTypeId; 
//	public String name; 
//	public Long parent; 
//	public Integer childOrder; 
//	public String description; 
//	public String gid;
	
	BaseBaseTypeDescriptionBean<?> baseTypeDescription;



	private void postConstruct() {
		baseTypeDescription = new  BaseTypeDescriptionBean<>();
	}
	
	public ReleaseGroupPrimaryTypeBean(Class<? extends ReleaseGroupPrimaryType<?>> class1) {
		super(class1);
		postConstruct();
	}


	public ReleaseGroupPrimaryTypeBean() {
		super(castClass());
		postConstruct();
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseGroupPrimaryType<?>>castClass() {
		return (Class<? extends ReleaseGroupPrimaryType<?>>) 
				ReleaseGroupPrimaryType.class.
				asSubclass(ReleaseGroupPrimaryType.class);
	}


	@Override
	public void setBaseTypeDescription(BaseBaseTypeDescriptionBean<?> k) {
		this.baseTypeDescription = k;
	}


	@Override
	public BaseBaseTypeDescriptionBean<?> getBaseTypeDescription() {
		return baseTypeDescription;
	}
	
}
