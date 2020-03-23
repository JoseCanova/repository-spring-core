package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.SequenceLongBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceLongBaseRepository<K extends SequenceLongBaseRepository<K,B> , 
B extends SequenceLongBase<B,Long>> extends  JpaRepository<B, Long> {
}
