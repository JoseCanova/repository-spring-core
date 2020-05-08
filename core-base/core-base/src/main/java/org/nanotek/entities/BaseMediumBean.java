package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.MediumBean;
import org.nanotek.beans.entity.Medium;
import org.nanotek.entities.immutables.MediumIdEntity;
import org.nanotek.entities.immutables.MediumNameEntity;

public interface BaseMediumBean
<K extends BaseBean<K,Medium<?>>> 
extends Base<K>,
BaseBean<K,Medium<?>>,
MutableMediumNameEntity<String>,
MutableMediumCountEntity<BaseMediumCountBean<?>>,
MutableMediumPositionEntity<BaseMediumPositionBean<?>>,
MutableMediumFormatEntity<BaseMediumFormatBean<?>>,
MutableMediumIdEntity<Long>,
MutableReleaseEntity<BaseReleaseBean<?>>{

	
	@Override
	default void setMediumId(Long t) {
		write(MutableMediumIdEntity.class,t);
	}
	
	@Override
	default Long getMediumId() {
		return read(MediumIdEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setMediumName(String k) {
		String theK = null;
		theK = isEmpty(k) ? "!" : k;
		write(MutableMediumNameEntity.class,theK);
	}
	
	default boolean isEmpty(String k) {
		return k == null ||  k.trim().isEmpty();
	}

	@Override
	default String getMediumName() {
		return read(MediumNameEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	default void setCount(Long l) {
		getMediumCount().setCount(l);
	}
	
	default Long getCount() {
		return getMediumCount().getCount();
	}
	
	default void setPosition(Long p) {
		getMediumPosition().setPosition(p);
	}
	
	default Long getPosition() {
		return getMediumPosition().getPosition();
	}
	
	default void setMediumFormatId(Long id) {
		getMediumFormat().setMediumFormatId(id);
	}
	
	default Long getMediumtFormatId() { 
		return getMediumFormat().getMediumFormatId();
	}
	
	default void setReleaseId(Long id) { 
		getRelease().setReleaseId(id);
	}
	
	default Long getReleaseId() {
		return getRelease().getReleaseId();
	}
	
	public static void main(String[] args) { 
		MediumBean<?> bean = new MediumBean<>();
	}

}
