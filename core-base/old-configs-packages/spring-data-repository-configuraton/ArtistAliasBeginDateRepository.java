package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ArtistAliasBeginDate;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistAliasBeginDateRepository<K extends ArtistAliasBeginDate<K>> extends 
BrainzBaseRepository<K>{
}
