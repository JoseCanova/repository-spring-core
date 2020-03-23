package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ArtistEndDate;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistEndDateRespository<K extends ArtistEndDate<K>> extends DatableBaseRepository<K>{
}
