package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ReleaseAliasSortName;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseAliasSortNameRepository<K extends ReleaseAliasSortName<K>> 
extends BrainzBaseRepository<K> {

}
