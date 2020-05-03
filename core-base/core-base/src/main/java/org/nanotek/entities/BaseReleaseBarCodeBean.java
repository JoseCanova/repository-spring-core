package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseBarCode;
import org.nanotek.entities.immutables.BarCodeEntity;

public interface BaseReleaseBarCodeBean
<K extends BaseBean<K,ReleaseBarCode<?>>> 
extends Base<K>,
BaseBean<K,ReleaseBarCode<?>>,
MutableBarCodeEntity<String>
{
	
	@Override
	default void setBarCode(String k) {
		write(MutableBarCodeEntity.class,k);
	}
	
	@Override
	default String getBarCode() {
		return read(BarCodeEntity.class).map(s->String.class.cast(s)).orElse("");
	}

}
