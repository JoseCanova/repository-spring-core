package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.BaseTypeDescriptionBean;
import org.nanotek.beans.entity.BaseTypeDescription;
import org.nanotek.entities.immutables.DescriptionBaseEntity;

public interface BaseBaseTypeDescriptionBean<K extends BaseBean<K,BaseTypeDescription<?>>>
extends
Base<K>,
BaseBean<K,BaseTypeDescription<?>>,
MutableDescriptionBaseEntity<String>{

	
	@Override
	default String getDescription() {
		return read(DescriptionBaseEntity.class).map(d ->String.class.cast(d)).orElse("");
	}
	
	@Override
	default void setDescription(String k) {
		write(MutableDescriptionBaseEntity.class,k);
	}
	
	public static void main(String[] args) {
		BaseTypeDescriptionBean bean = new BaseTypeDescriptionBean(BaseTypeDescription.class);
		bean.setDescription("this is a description");
		System.out.println(bean.getDescription());
	}
}
