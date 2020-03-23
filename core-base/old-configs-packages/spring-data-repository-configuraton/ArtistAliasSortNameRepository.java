package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ArtistAliasSortName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistAliasSortNameRepository<K extends ArtistAliasSortName <K>> 
extends BrainzBaseRepository<K>{
}
