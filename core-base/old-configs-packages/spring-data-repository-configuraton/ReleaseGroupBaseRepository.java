package org.nanotek.repository.jpa;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableReleaseGroupIdEntity;

public interface ReleaseGroupBaseRepository<K extends MutableReleaseGroupIdEntity<ID>, ID extends Serializable> {

	Optional<K> findByReleaseGroupId(@NotNull ID releaseGroupId);
	
}
