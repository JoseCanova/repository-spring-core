package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.ReleaseGroupPrimaryTypeBean;
import org.nanotek.beans.entity.ReleaseGroupPrimaryType;
import org.nanotek.entities.immutables.ChildOrderEntity;
import org.nanotek.entities.immutables.TypeNameEntity;

public interface BaseReleaseGroupPrimaryTypeBean
<K extends BaseBean<K,ReleaseGroupPrimaryType<?>>> 
extends Base<K>,
BaseBean<K,ReleaseGroupPrimaryType<?>>,
MutableGidEntity<UUID>,
MutableTypeNameEntity<String>,
MutableTypeIdEntity<Long>,
MutableParentEntity<Long>,
MutableChildOrderEntity<Long>,
MutableBaseTypeDescriptionEntity<BaseBaseTypeDescriptionBean<?>>,
MutableDescriptionBaseEntity<String>{

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
	default String getTypeName() {
		return read(TypeNameEntity.class).map(s->String.class.cast(s)).orElse("");
	}

	@Override
	default void setTypeName(String k) {
		write(MutableTypeNameEntity.class,k);
	}

	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(g ->UUID.class.cast(g)).orElse(UUID.randomUUID());
	}

	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}

	@Override
	default String getDescription() {
		return getBaseTypeDescription().getDescription();
	}

	@Override
	default void setDescription(String k) {
		getBaseTypeDescription().setDescription(k);
	}


	public static  void main(String[] args) {
		ReleaseGroupPrimaryTypeBean bean = new ReleaseGroupPrimaryTypeBean(ReleaseGroupPrimaryType.class);
		bean.setTypeName("name");
		System.out.println(bean.getTypeName());
		bean.setParent(1000L);
		System.out.println(bean.getParent());
		bean.setDescription("this is  a rescription");
		System.out.println(bean.getDescription());
		bean.setChildOrder(100L);
		System.out.println(bean.getChildOrder());
		bean.setTypeId(1000l);
		System.out.println(bean.getTypeId());
		bean.setGid(UUID.randomUUID());
		System.out.println(bean.getGid().toString());
		
	}

}