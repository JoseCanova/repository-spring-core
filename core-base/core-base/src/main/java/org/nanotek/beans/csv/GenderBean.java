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
		super((Class<? extends Gender<?>>) Gender.class);
		baseBaseTypeDescription = new BaseTypeDescriptionBean();
	}
	
	public GenderBean(Class<? extends Gender<?>> class1) {
		super(class1);
		baseBaseTypeDescription = new BaseTypeDescriptionBean();
	}
	
	public BaseBaseTypeDescriptionBean<?> getBaseTypeDescription() {
		return baseBaseTypeDescription;
	}
	@Override
	public void setBaseTypeDescription(BaseBaseTypeDescriptionBean<?> k) {
			this.baseBaseTypeDescription = k;
	}

	@Override
	public int compareTo(GenderBean<K> o) {
		return 0;
	}


}
