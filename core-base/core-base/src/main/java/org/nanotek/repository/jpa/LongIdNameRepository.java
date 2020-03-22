package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.LongIdName;
import org.nanotek.entities.MutableNameEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LongIdNameRepository<K extends LongIdName<K>> 
extends BrainzBaseRepository<K>, 
MutableNameEntity<String>{

}
