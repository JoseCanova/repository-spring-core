package org.nanotek.repository.jpa;

import java.io.Serializable;

import org.nanotek.BaseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseSequenceLongBaseEntityRepository 
<K extends BaseEntity<K,ID>,ID extends Serializable> 
extends 
BaseEntityRepository<K, ID>{
}
