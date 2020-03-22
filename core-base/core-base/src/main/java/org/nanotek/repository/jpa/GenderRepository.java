package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.Gender;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository <K extends Gender<K>>extends 
BrainzBaseRepository<K>{
}
