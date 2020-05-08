package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.MediumCount;
import org.nanotek.entities.immutables.CountEntity;

public interface BaseMediumCountBean
<K extends BaseBean<K,MediumCount<?>>> 
extends 
Base<K>,
BaseBean<K,MediumCount<?>>,
MutableCountEntity<Long>
{

	@Override
	default void setCount(Long k) {
		write(MutableCountEntity.class,k);
	}
	
	@Override
	default Long getCount() {
		return read(CountEntity.class).map(c->Long.class.cast(c)).orElse(Long.MIN_VALUE);
	}
	
}
