package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.entities.immutables.ChildOrderEntity;
import org.nanotek.entities.immutables.DescriptionEntity;

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
	default Long getParent() {
			return read(ParentEntity.class).filter(v-> v!=null).map(v -> Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setChildOrder(Long k) {
		write(MutableChildOrderEntity.class,k);
	}
	
	@Override
	default Long getChildOrder() {
		return read(ChildOrderEntity.class).filter(v-> v!=null).map(v -> Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setDescription(String k) {
		write(MutableDescriptionEntity.class,k);
	}
	
	@Override
	default String getDescription() {
		return read(DescriptionEntity.class).filter(v-> v!=null).map(v -> String.class.cast(v)).orElse(new String());
	}
	
	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}
	
	default String getName() {
		return read(NameEntity.class).filter(v-> v!=null).map(v -> String.class.cast(v)).orElse(new String());
	}
	
}
