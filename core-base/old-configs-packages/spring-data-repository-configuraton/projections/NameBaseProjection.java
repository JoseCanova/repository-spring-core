package org.nanotek.repository.jpa.projections;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.entities.MutableNameEntity;

public @Projection interface  NameBaseProjection<E extends BrainzBaseEntity<?> , N extends Serializable>{
	Iterable<E> findByNameContainingIgnoreCase(@Valid @NotNull N name);
}
