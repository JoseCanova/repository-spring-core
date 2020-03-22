package org.nanotek.repository.jpa.projections;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.BaseType;

public interface BaseTypeProjection<T extends BaseType<?>>{
	Optional<T> findByTypeId(@NotNull Long typeId);
}
