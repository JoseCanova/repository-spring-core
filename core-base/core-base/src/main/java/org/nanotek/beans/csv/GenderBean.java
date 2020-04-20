package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Gender;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.entities.BaseGenderBean;
import org.nanotek.proxy.ProxyBase;

public class GenderBean
<K extends BaseBean<GenderBean<K>,Gender<?>>> 
extends ProxyBase<GenderBean<K>,Gender<?>>
implements BaseGenderBean<GenderBean<K>>{

	public static final long serialVersionUID = -1492542566677551150L;

	BaseBaseTypeDescriptionBean<?>  baseBaseTypeDescription;

	public GenderBean() {
		super(castClass());
		postConstruct();
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Gender<?>> castClass() {
		return (Class<? extends Gender<?>>) Gender.class.asSubclass(Gender.class);
	}

	public GenderBean(Class<? extends Gender<?>> class1) {
		super(class1);
		postConstruct();
	}

	void postConstruct(){
		baseBaseTypeDescription = new BaseTypeDescriptionBean();
	}
	
	public BaseBaseTypeDescriptionBean<?> getBaseTypeDescription() {
		return baseBaseTypeDescription;
	}
	@Override
	public void setBaseTypeDescription(BaseBaseTypeDescriptionBean<?> k) {
		this.baseBaseTypeDescription = k;
	}

}
