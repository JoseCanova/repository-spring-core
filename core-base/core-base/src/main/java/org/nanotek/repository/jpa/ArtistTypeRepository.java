package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ArtistType;
import org.nanotek.repository.jpa.projections.BaseTypeProjection;
import org.nanotek.repository.jpa.projections.NameBaseProjection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value="ArtistTypeRepository")
public interface ArtistTypeRepository<K extends ArtistType<K>> extends 
BaseTypeProjection<K> , 
BrainzBaseRepository<K>, 
NameBaseProjection<K,String>{
}
