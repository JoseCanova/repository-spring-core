package org.nanotek.repository;

import java.io.Serializable;

import org.nanotek.BaseEntity;

public interface BaseSequenceLongBaseEntityRepository 
<K extends BaseEntity<K,ID>,ID extends Serializable> 
extends 
BaseEntityRepository<K, ID>{
}
