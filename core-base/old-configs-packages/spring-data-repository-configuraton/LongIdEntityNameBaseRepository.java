package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.LongIdName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LongIdEntityNameBaseRepository
<K extends LongIdName<K>> extends BrainzBaseRepository<K> {
}
