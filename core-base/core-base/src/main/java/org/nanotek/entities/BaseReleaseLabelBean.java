package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Release;
import org.nanotek.beans.entity.ReleaseLabel;
import org.nanotek.entities.immutables.ReleaseLabelIdEntity;

public interface BaseReleaseLabelBean
<K extends BaseBean<K,ReleaseLabel<?>>> 
extends Base<K>,
BaseBean<K,ReleaseLabel<?>>,
MutableReleaseLabelIdEntity<Long>,
MutableLabelReleaseEntity<BaseLabelBean<?>>,
MutableReleaseLabelCatalogEntity<BaseReleaseLabelCatalogBean<?>>,
MutableReleaseEntity<BaseReleaseBean<?>>
{

	@Override
	default Long getReleaseLabelId() {
		return read(ReleaseLabelIdEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setReleaseLabelId(Long k) {
		write(MutableReleaseLabelIdEntity.class,k);
	}
	
	default void setReleaseLabelNumber(String n) { 
		getReleaseLabelCatalog().setReleaseLabelNumber(n);
	}
	
	default String getReleaseLabelNumber() { 
		return getReleaseLabelCatalog().getReleaseLabelNumber();
	}
	
	default void setLabelId(Long id) {
		getLabelRelease().setLabelId(id);
	}
	
	default Long getLableId() { 
		return getLabelRelease().getLabelId();
	}
	
	default void setReleaseId(Long id) { 
		getRelease().setReleaseId(id);
	}
	
	default Long getReleaseId() { 
		return getRelease().getReleaseId();
	}
	
}
