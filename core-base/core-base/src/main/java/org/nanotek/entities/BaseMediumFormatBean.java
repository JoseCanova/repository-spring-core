package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.MediumFormatBean;
import org.nanotek.beans.entity.MediumFormat;
import org.nanotek.entities.immutables.DescriptionEntity;
import org.nanotek.entities.immutables.DiscIdEntity;
import org.nanotek.entities.immutables.MediumFormatIdEntity;
import org.nanotek.entities.immutables.MediumFormatNameEntity;
import org.nanotek.entities.immutables.YearEntity;

public interface BaseMediumFormatBean
<K extends BaseBean<K,MediumFormat<?>>> 
extends Base<K>,
BaseBean<K,MediumFormat<?>>,
MutableDescriptionEntity<String>,
MutableMediumFormatNameEntity<String>,
MutableYearEntity<Integer>,
MutableMediumFormatIdEntity<Long>,
MutableDiscIdEntity<String>,
MutableParentEntity<Long>,
MutableGidEntity<UUID>{


	@Override
	default Long getParent() {
		return read(ParentEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}

	@Override
	default void setParent(Long k) {
		write(MutableParentEntity.class,k);
	}
	
	@Override
	default Integer getYear() {
		return read(YearEntity.class).map(i ->Integer.class.cast(i)).orElse(Integer.MIN_VALUE);
	}
	
	
	@Override
	default void setYear(Integer k) {
		write(MutableYearEntity.class,k);
	}
	
	@Override
	default String getDescription() {
		return read(DescriptionEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setDescription(String k) {
		write(MutableDescriptionEntity.class,k);
	}
	
	
	@Override
	default Long getMediumFormatId() {
		return read(MediumFormatIdEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setMediumFormatId(Long t) {
		write(MutableMediumFormatIdEntity.class,t);
	}
	
	@Override
	default String getMediumFormatName() {
		return read(MediumFormatNameEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setMediumFormatName(String k) {
		write(MutableMediumFormatNameEntity.class,k);
	}
	
	@Override
	default void setDiscId(String t) {
		write(MutableDiscIdEntity.class,t);
	}
	
	@Override
	default String getDiscId() {
		return read(DiscIdEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(u->UUID.class.cast(u)).orElse(UUID.randomUUID());
	}

	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}
	
	
	
	
	public static void main(String[] args) { 
		MediumFormatBean<?> bean = new MediumFormatBean<>();
		bean.setDescription("this i a description");
		System.out.println(bean.getDescription());
	}
}
