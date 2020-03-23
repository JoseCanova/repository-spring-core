package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ArtistBeginDate;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistBeginDateRepository<K extends ArtistBeginDate<K>> extends DatableBaseRepository<K>{
}
