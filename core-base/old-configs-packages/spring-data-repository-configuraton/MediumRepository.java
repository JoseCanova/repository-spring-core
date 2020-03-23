package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.Medium;
import org.nanotek.repository.jpa.projections.NameBaseProjection;
import org.springframework.stereotype.Repository;

@Repository
public interface MediumRepository<K extends Medium<K>> 
extends BrainzBaseRepository<K>
,NameBaseProjection<K, String>{
}
