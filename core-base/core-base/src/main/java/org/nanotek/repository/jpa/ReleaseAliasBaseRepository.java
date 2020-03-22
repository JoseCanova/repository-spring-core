package org.nanotek.repository.jpa;

import java.io.Serializable;
import java.util.Optional;

import org.nanotek.beans.entity.ReleaseAlias;
import org.nanotek.entities.MutableReleaseAliasIdEntity;
import org.nanotek.repository.jpa.projections.ReleaseAliasIdEntityProjection;

public interface ReleaseAliasBaseRepository
<K extends ReleaseAlias<K>, ID extends Serializable> 
extends BrainzBaseRepository<K> , ReleaseAliasIdEntityProjection<K,ID>{

	Optional<K> findByReleaseAliasId(ID id);
	
}
