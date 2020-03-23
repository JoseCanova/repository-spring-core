package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ReleasePackaging;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleasePackagingRepository<K extends ReleasePackaging<K>> 
extends  BrainzBaseRepository<K> ,
ReleasePackagingBaseRepository<K,Long>{
}
