package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ReleaseAliasLocale;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseAliasLocaleRepository <K extends ReleaseAliasLocale<K>>
extends BrainzBaseRepository<K> {
}
