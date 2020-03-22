package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ArtistType;
import org.nanotek.repository.jpa.projections.BaseTypeProjection;
import org.nanotek.repository.jpa.projections.NameBaseProjection;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistTypeRepository<K extends ArtistType<K>> extends 
BaseTypeProjection<K> , 
BrainzBaseRepository<K>, 
NameBaseProjection<K,String>{
}
