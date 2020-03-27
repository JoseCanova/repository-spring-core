package org.nanotek.entities;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.assertj.core.util.introspection.ClassUtils;
import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.MutatorSupport;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.opencsv.CsvBaseBean;

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
	
	default <V>  void write(Class<?> clazz , V v){ 
		MutatorSupport
		.getPropertyDescriptor(clazz)
		.ifPresent(m -> getCsvBaseBean().set(m.getName(),v));
	}
	
}
