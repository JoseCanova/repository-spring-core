package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.SequenceLongBase;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceLongBaseRepository<K extends SequenceLongBaseRepository<K,B> , 
B extends SequenceLongBase<B,Long>> 
extends  BaseSequenceLongBaseEntityRepository<B, Long> {
}
