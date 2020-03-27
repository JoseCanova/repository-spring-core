package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaType;

public interface BaseAreaTypeBean<K extends BaseBean<K,AreaType<?>>> 
extends Base<K>,
BaseBean<K,AreaType<?>>,
MutableTypeIdEntity<Long>,
MutableParentEntity<Long>,
MutableChildOrderEntity<Long>,
MutableDescriptionEntity<String>,
MutableNameEntity<String>{
	
	@Override
	default void setTypeId(Long k) {
		write(MutableTypeIdEntity.class,k);
	}
	
	@Override
	default Long getTypeId() {
		return read(TypeIdEntity.class).filter(v-> v!=null).map(v -> Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setParent(Long k) {
			write(MutableParentEntity.class , k);
	}

	@Override
	default void setChildOrder(Long k) {
		write(MutableChildOrderEntity.class,k);
	}
	
	@Override
	default void setDescription(String k) {
		write(MutableDescriptionEntity.class,k);
	}
	
	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}
	
}
