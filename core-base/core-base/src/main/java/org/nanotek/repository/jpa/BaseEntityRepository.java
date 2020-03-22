package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.BrainzBaseEntity;

public interface BaseEntityRepository<K extends BrainzBaseEntity<K>> 
extends SequenceLongBaseRepository<BaseEntityRepository<K>, K>{

}
