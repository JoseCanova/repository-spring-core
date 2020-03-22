package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.Recording;
import org.nanotek.repository.jpa.projections.NameBaseProjection;
import org.nanotek.repository.jpa.projections.RecordingBaseProjection;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordingRepository<K extends Recording<K>> extends 
BrainzBaseRepository<K> , 
RecordingBaseProjection<K>,
NameBaseProjection<K,String>{
}
