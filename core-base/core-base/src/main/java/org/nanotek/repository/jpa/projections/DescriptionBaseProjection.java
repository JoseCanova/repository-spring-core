package org.nanotek.repository.jpa.projections;

import java.io.Serializable;

import org.nanotek.BaseDescriptionIdBase;

public interface DescriptionBaseProjection<D extends Serializable, K extends BaseDescriptionIdBase<?,D,?>>{
	Iterable<K> findByDescriptionContaining(D d);
}
