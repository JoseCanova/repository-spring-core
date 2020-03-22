package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ReleaseBarCode;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseBarCodeRepository<K extends ReleaseBarCode<K>> extends 
BrainzBaseRepository<K> {
}
