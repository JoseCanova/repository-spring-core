package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.BrainzBaseEntity;

public interface BrainzBaseRepository<K extends BrainzBaseEntity<K>>
extends SequenceLongBaseRepository<BrainzBaseRepository<K>,K>
{	
}
