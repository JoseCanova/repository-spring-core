package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseLabelCatalog;
import org.nanotek.entities.immutables.ReleaseLabelNumberEntity;

public interface BaseReleaseLabelCatalogBean
<K extends BaseBean<K,ReleaseLabelCatalog<?>>> 
extends Base<K>,
BaseBean<K,ReleaseLabelCatalog<?>>,
MutableReleaseLabelNumberEntity<String>
{

	@Override
	default String getReleaseLabelNumber() {
		return read(ReleaseLabelNumberEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setReleaseLabelNumber(String k) {
		write(MutableReleaseLabelNumberEntity.class,k);
	}
	
}
