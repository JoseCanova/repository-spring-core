package org.nanotek.repository.jpa.projections;

import java.io.Serializable;
import java.util.Optional;

import org.nanotek.beans.entity.BrainzBaseEntity;

public interface BaseDescriptionBaseProjection<K extends BrainzBaseEntity<K>, ID extends Serializable>{
	Optional<?> findByDescription(ID id);
	Iterable<K> findByDescriptionContaining(ID d);
}
