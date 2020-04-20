package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.RecordingAliasTypeBean;
import org.nanotek.beans.entity.RecordingAliasType;
import org.nanotek.entities.immutables.ChildOrderEntity;
import org.nanotek.entities.immutables.TypeNameEntity;

public interface BaseRecordingAliasTypeBean
<K extends BaseBean<K,RecordingAliasType<?>>> 
extends Base<K>,
BaseBean<K,RecordingAliasType<?>>,
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

	public static void main(String[] args) { 
		RecordingAliasTypeBean<?> bean = new RecordingAliasTypeBean<>();
	}
}
