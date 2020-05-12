package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.TrackPosition;
import org.nanotek.entities.immutables.PositionEntity;

public interface BaseTrackPositionBean
<K extends BaseBean<K,TrackPosition<?>>>
extends Base<K>,
BaseBean<K,TrackPosition<?>>,
MutablePositionEntity<Long>{
	@Override
	default void setPosition(Long k) {
		write(MutablePositionEntity.class,k);
	}
	
	@Override
	default Long getPosition() {
		return read(PositionEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
}
