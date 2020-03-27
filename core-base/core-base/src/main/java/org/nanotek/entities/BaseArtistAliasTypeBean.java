package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistAliasType;
import org.nanotek.beans.entity.BaseTypeDescription;
import org.nanotek.entities.immutables.AliasIdEntity;
import org.nanotek.entities.immutables.ChildOrderEntity;
import org.nanotek.entities.immutables.DescriptionEntity;

/**
 * public interface BaseAreaBean<K extends BaseBean<K,Area<?>>> extends 
Base<K>,
BaseBean<K,Area<?>>
 * @author jose
 *
 * @param <B>
 */

public interface BaseArtistAliasTypeBean
<B extends BaseBean<B,ArtistAliasType<?>>> 
extends Base<B> ,
BaseBean<B,ArtistAliasType<?>>,
MutableAliasIdEntity<Long>,
MutableTypeIdEntity<Long>,
MutableNameEntity<String>,
MutableParentEntity<Long>,
MutableChildOrderEntity<Long>,
MutableDescriptionEntity<String>
{
	@Override
	default Long getAliasId() {
		return read(AliasIdEntity.class).map(m -> Long.class.cast(m)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setAliasId(Long k) {
			write(MutableAliasIdEntity.class, k);
	}

	@Override
	default String getName() {
		return read(NameEntity.class).map(m ->String.class.cast(m)).orElse("");
	}

	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}

	@Override
	default Long getChildOrder() {
		return read(ChildOrderEntity.class).map(m ->Long.class.cast(m)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setChildOrder(Long k) {
		write(MutableChildOrderEntity.class,k);
	}
	
	@Override
	default Long getParent() {
		return read(ParentEntity.class).map(m->Long.class.cast(m)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setParent(Long k) {
			write(MutableParentEntity.class,k);
	}

	@Override
	default Long getTypeId() {
		return read(TypeIdEntity.class).map(m->Long.class.cast(m)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default String getDescription() {
		return read(DescriptionEntity.class).map(m->BaseTypeDescription.class.cast(m)).map(m->m.getDescription()).orElse("");
	}
	
	@Override
	default void setDescription(String k) {
		write(MutableDescriptionEntity.class,k).map(m->new BaseTypeDescription<>(m));
	}
	
	@Override
	default void setTypeId(Long k) {
		write(MutableTypeIdEntity.class,k);
	}
	
}