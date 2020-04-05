package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.SequenceLongBase;
import org.nanotek.repository.BaseSequenceLongBaseEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceLongBaseRepository<
B extends SequenceLongBase<B,Long>> 
extends  BaseSequenceLongBaseEntityRepository<B, Long> {
}
