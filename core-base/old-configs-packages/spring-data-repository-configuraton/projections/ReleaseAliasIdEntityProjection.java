package org.nanotek.repository.jpa.projections;

import java.io.Serializable;
import java.util.Optional;

import org.nanotek.entities.MutableReleaseAliasIdEntity;

public interface ReleaseAliasIdEntityProjection<T extends MutableReleaseAliasIdEntity<?> , ID extends Serializable> {
	Optional<T> findByReleaseAliasId(ID id);
}
