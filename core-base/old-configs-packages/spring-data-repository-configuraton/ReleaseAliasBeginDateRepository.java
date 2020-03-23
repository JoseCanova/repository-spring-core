package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ReleaseAliasBeginDate;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseAliasBeginDateRepository
<K extends ReleaseAliasBeginDate<K>>
extends BrainzBaseRepository<K> {
}