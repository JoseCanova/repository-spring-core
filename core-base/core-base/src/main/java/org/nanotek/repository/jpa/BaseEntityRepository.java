package org.nanotek.repository.jpa;

import java.io.Serializable;

import org.nanotek.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseEntityRepository
<K extends BaseEntity<K,ID>,ID extends Serializable> 
extends 
JpaRepository<K, ID>
{

}
