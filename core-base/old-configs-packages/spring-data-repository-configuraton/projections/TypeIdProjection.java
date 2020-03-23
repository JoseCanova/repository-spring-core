package org.nanotek.repository.jpa.projections;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableTypeIdEntity;

public interface TypeIdProjection<S extends MutableTypeIdEntity<K> , K extends Serializable> {
		Optional<S> findByTypeId(@NotNull K s);
}
