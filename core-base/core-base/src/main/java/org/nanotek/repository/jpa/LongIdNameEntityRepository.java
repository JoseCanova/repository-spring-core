package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.BaseType;
import org.springframework.stereotype.Repository;

@Repository
public interface LongIdNameEntityRepository<K extends BaseType<K>>
extends LongIdEntityNameBaseRepository<K> {
}