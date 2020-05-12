package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.Numerable;
import org.nanotek.beans.entity.TrackNumber;

public interface BaseTrackNumberBean
<K extends BaseBean<K,TrackNumber>>
extends Base<K>,
BaseBean<K,TrackNumber>,
MutableNumberEntity<String>,
Numerable<String>
{
	@Override
	default String getNumber() {
		return read(Numerable.class).map(s->String.class.cast(s)).orElse("0");
	}
	
	@Override
	default void setNumber(String t) {
		write(MutableNumberEntity.class,t);
	}
	
}
