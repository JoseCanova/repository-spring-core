package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ArtistSortName;
import org.nanotek.repository.jpa.projections.SortNameProjection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value="ArtistSortNameRepository")
public interface ArtistSortNameRepository <K extends ArtistSortName<K>> 
extends  
BrainzBaseRepository<K>,
SortNameProjection<K>{
}
