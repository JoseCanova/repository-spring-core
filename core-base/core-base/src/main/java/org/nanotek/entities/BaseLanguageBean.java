package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.LanguageBean;
import org.nanotek.beans.entity.Language;
import org.nanotek.entities.immutables.FrequencyEntity;
import org.nanotek.entities.immutables.IsoCode1Entity;
import org.nanotek.entities.immutables.IsoCode2BEntity;
import org.nanotek.entities.immutables.IsoCode2TEntity;
import org.nanotek.entities.immutables.IsoCode3Entity;
import org.nanotek.entities.immutables.LanguageIdEntity;
import org.nanotek.entities.immutables.LanguageNameEntity;

public interface BaseLanguageBean
<K extends BaseBean<K,Language<?>>> 
extends Base<K>,
BaseBean<K,Language<?>>,
MutableLanguageIdEntity<Long>,
MutableIsoCode2TEntity<String>,
MutableIsoCode2BEntity<String>,
MutableFrequencyEntity<Long>,
MutableIsoCode3Entity<String>,
MutableLanguageNameEntity<String>,
MutableIsoCode1Entity<String>{

	
	@Override
	default Long getLanguageId() {
		return read(LanguageIdEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setLanguageId(Long k) {
		write(MutableLanguageIdEntity.class,k);
	}
	
	
	@Override
	default String getIsoCode2B() {
		return read(IsoCode2BEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setIsoCode2B(String k) {
		write(MutableIsoCode2BEntity.class,k);
	}
	
	@Override
	default String getIsoCode2T() {
		return read(IsoCode2TEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setIsoCode2T(String k) {
		write(MutableIsoCode2TEntity.class,k);
	}
	
	@Override
	default String getIsoCode3() {
		return read(IsoCode3Entity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setIsoCode3(String k) {
		write(MutableIsoCode3Entity.class,k);
	}
	
	@Override
	default Long getFrequency() {
		return read(FrequencyEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}	
	
	@Override
	default void setFrequency(Long k) {
		write(MutableFrequencyEntity.class,k);
	}
	
	@Override
	default String getLanguageName() {
		return read(LanguageNameEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setLanguageName(String t) {
		write(MutableLanguageNameEntity.class,t);
	}
	
	
	@Override
	default String getIsoCode1() {
		return read(IsoCode1Entity.class).map(s->String.class.cast(s)).orElse("");
	}
		
	@Override
	default void setIsoCode1(String t) {
		write(MutableIsoCode1Entity.class,t);
	}
	
	
	public static void main(String[] args) { 
		LanguageBean<?> language = new LanguageBean<>();
	}
}
