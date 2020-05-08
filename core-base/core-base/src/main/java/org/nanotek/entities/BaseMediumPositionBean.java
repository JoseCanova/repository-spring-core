package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.MediumPosition;
import org.nanotek.entities.immutables.PositionEntity;

public interface BaseMediumPositionBean
<K extends BaseBean<K,MediumPosition>> 
extends 
Base<K>,
BaseBean<K,MediumPosition>,
MutablePositionEntity<Long>
{
	
	@Override
	default void setPosition(Long k) {
		write(MutablePositionEntity.class,k);
	}
	
	@Override
	default Long getPosition() {
		return read(PositionEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
}
