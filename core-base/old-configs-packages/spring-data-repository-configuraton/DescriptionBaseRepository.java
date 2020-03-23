package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.DescriptionBase;
import org.nanotek.repository.jpa.projections.BaseDescriptionBaseProjection;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionBaseRepository<K extends DescriptionBase<K>> 
extends BaseDescriptionBaseProjection<K,String> , BrainzBaseRepository<K>{
}
