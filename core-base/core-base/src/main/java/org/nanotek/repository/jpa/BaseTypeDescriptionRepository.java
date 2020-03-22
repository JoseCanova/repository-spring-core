package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.BaseTypeDescription;
import org.nanotek.repository.jpa.projections.DescriptionBaseProjection;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseTypeDescriptionRepository<B extends BaseTypeDescription<B>> extends  BrainzBaseRepository<B> , 
DescriptionBaseProjection<String, B>{
}
