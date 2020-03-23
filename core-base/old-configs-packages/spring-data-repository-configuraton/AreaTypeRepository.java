package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.AreaType;
import org.nanotek.repository.jpa.projections.BaseTypeProjection;
import org.nanotek.repository.jpa.projections.NameBaseProjection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value="AreaTypeRepository")
public interface AreaTypeRepository<K extends AreaType<K>> extends  
BaseTypeProjection<K> ,  
BrainzBaseRepository<K> , 
NameBaseProjection<K,String>{ 
}
