package org.nanotek.repository.jpa;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableReleaseIdEntity;

public interface ReleaseBaseRepository
<T extends MutableReleaseIdEntity<ID> , ID extends Serializable> {
	Optional<T> findByReleaseId(@NotNull ID releaseId);
}
