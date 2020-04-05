package org.nanotek.repository;

import java.io.Serializable;

import org.nanotek.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseEntityRepository
<K extends BaseEntity<K,ID>,ID extends Serializable> 
extends 
JpaRepository<K, ID>
{

}
