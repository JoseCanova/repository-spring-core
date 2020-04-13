package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BrainzBaseEntityRepository 
<B extends BrainzBaseEntity<B>>
extends  SequenceLongBaseRepository<B> {

}
