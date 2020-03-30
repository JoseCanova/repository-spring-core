package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.BaseTypeDescription;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.proxy.ProxyBase;

/**
 * MutableDescriptionBaseEntity<String>
 * @author jose
 *
 */
public class BaseTypeDescriptionBean
<K extends BaseBean<BaseTypeDescriptionBean<K>,BaseTypeDescription<?>>> 
extends ProxyBase<BaseTypeDescriptionBean<K>,BaseTypeDescription<?>>
implements BaseBaseTypeDescriptionBean<BaseTypeDescriptionBean<K>>{

	private static final long serialVersionUID = -8448752427285481821L;

	public <ID extends AreaType<?>> BaseTypeDescriptionBean() {
		super((Class<? extends BaseTypeDescription<?>>) BaseTypeDescription.class);
	}

	public BaseTypeDescriptionBean(Class<BaseTypeDescription<?>> class1) {
		super(class1);
	}

}
