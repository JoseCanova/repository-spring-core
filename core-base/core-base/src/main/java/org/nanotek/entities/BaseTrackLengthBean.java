package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.TrackLength;
import org.nanotek.entities.immutables.LengthEntity;

public interface BaseTrackLengthBean 
<K extends BaseBean<K,TrackLength>>
extends Base<K>,
BaseBean<K,TrackLength>,
MutableLengthEntity<Long>
{
	@Override
	default void setLength(Long k) {
		write(MutableLengthEntity.class,k);
	}
	
	@Override
	default Long getLength() {
		return read(LengthEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
	
	
}
