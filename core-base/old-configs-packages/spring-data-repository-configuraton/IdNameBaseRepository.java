package org.nanotek.repository.jpa;

import org.nanotek.IdNameBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdNameBaseRepository <K  extends IdNameBase<?,?,Long>> extends JpaRepository<K,Long>{
}
