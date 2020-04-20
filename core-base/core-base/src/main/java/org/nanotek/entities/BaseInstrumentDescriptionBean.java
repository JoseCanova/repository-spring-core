package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.InstrumentDescriptionBean;
import org.nanotek.beans.entity.InstrumentDescription;
import org.nanotek.entities.immutables.DescriptionBaseEntity;

public interface BaseInstrumentDescriptionBean
<K extends BaseBean<K,InstrumentDescription<?>>>
extends
Base<K>,
BaseBean<K,InstrumentDescription<?>>,
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
		BaseInstrumentDescriptionBean bean = new InstrumentDescriptionBean(InstrumentDescription.class);
		bean.setDescription("this is a description");
		System.out.println(bean.getDescription());
	}
}
