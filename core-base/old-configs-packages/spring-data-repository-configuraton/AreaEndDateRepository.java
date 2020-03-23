package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.AreaEndDate;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaEndDateRepository<K extends AreaEndDate<K>> extends DatableBaseRepository<K>{
}
