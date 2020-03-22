package org.nanotek.repository.jpa.projections;

import javax.validation.constraints.NotNull;

public interface SortNameProjection<T> {
	Iterable<T> findBySortNameContainingIgnoreCase(@NotNull String name);
}
