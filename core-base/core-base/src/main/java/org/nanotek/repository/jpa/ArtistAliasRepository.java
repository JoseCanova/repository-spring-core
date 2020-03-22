package org.nanotek.repository.jpa;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.ArtistAlias;
import org.nanotek.repository.jpa.projections.NameBaseProjection;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistAliasRepository<K extends ArtistAlias<K>> 
extends BrainzBaseRepository<K> , NameBaseProjection<K,String>{
	Optional<K> findByAliasId(@NotNull Long aliasId);
}
