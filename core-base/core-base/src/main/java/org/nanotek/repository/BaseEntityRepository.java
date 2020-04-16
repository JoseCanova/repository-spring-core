package org.nanotek.repository;

import java.io.Serializable;

import org.nanotek.BaseEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value="brainzBaseEntityRepository")
public interface BaseEntityRepository
<K extends BaseEntity<K,ID>,ID extends Serializable> 
extends 
JpaRepository<K, ID>,QueryByExampleExecutor<K>
{
}
