package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.GenderBean;
import org.nanotek.beans.entity.Gender;
import org.nanotek.entities.immutables.ChildOrderEntity;
import org.nanotek.entities.immutables.TypeNameEntity;

public interface BaseGenderBean
<K extends BaseBean<K,Gender<?>>> 
extends Base<K>,
BaseBean<K,Gender<?>>,
MutableGidEntity<UUID>,
MutableTypeNameEntity<String>,
MutableTypeIdEntity<Long>,
MutableParentEntity<Long>,
MutableChildOrderEntity<Long>,
MutableBaseTypeDescriptionEntity<BaseBaseTypeDescriptionBean<?>>,
MutableDescriptionBaseEntity<String>{
	
	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}
	
	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(v -> UUID.class.cast(v)).orElse(UUID.randomUUID());
	}
	
	@Override
	default void setTypeName(String k) {
		write(MutableTypeNameEntity.class,k);
	}
	
	@Override
	default String getTypeName() {
		return read(TypeNameEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setTypeId(Long k) {
		write(MutableTypeIdEntity.class,k);
	}
	
	@Override
	default Long getTypeId() {
		return read(TypeIdEntity.class).map(v->Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default Long getChildOrder() {
		return read(ChildOrderEntity.class).map(v->Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setChildOrder(Long k) {
		write(MutableChildOrderEntity.class,k);
	}
	
	
	@Override
	BaseBaseTypeDescriptionBean<?> getBaseTypeDescription();
	
	
	@Override
	void setBaseTypeDescription(BaseBaseTypeDescriptionBean<?> k);
	
	@Override
	default String getDescription() {
		return getBaseTypeDescription().getDescription();
	}
	
	default void setDescription(String k) {
		getBaseTypeDescription().setDescription(k);
	}
	
	@Override
	default void setParent(Long k) {
			write(MutableParentEntity.class,k);
	}
	
	@Override
	default Long getParent() {
		return read(ParentEntity.class).map(p->Long.class.cast(p)).orElse(Long.MIN_VALUE);
	}
	
	public static void main(String[] args) {
		GenderBean bean = new GenderBean();
		bean.setTypeName("this is a name");
		System.out.println(bean.getTypeName());
	}

}
