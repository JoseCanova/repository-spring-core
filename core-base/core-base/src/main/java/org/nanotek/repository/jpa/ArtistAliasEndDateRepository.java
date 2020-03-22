package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ArtistAliasEndDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistAliasEndDateRepository<K extends ArtistAliasEndDate<K>> extends BrainzBaseRepository<K> {
}
