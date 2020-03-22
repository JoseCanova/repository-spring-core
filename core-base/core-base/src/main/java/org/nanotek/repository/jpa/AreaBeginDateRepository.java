package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.AreaBeginDate;
import org.springframework.stereotype.Repository;

@Repository	
public interface AreaBeginDateRepository<K extends AreaBeginDate<K>> extends DatableBaseRepository<K>{
}
