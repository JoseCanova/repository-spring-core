package org.nanotek.repository.jpa;

import java.util.Optional;

import org.nanotek.beans.entity.ReleaseAlias;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseAliasJpaRepository<K extends ReleaseAlias<K>> 
extends BrainzBaseRepository<K>{
	Optional<K> findByReleaseAliasId(Long id);
}
