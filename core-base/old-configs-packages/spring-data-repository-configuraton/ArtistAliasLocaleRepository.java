package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ArtistAliasLocale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistAliasLocaleRepository<K extends ArtistAliasLocale<K>> extends BrainzBaseRepository<K> {
}
