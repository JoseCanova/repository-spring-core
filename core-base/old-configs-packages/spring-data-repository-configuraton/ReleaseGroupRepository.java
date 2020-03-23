package org.nanotek.repository.jpa;

import java.util.Optional;

import org.nanotek.beans.entity.ReleaseGroup;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseGroupRepository<K extends ReleaseGroup<K>> extends LongIdGidNameEntityRepository<K> {
	Optional<K> findByReleaseGroupId(Long releaseGroupId);
}
