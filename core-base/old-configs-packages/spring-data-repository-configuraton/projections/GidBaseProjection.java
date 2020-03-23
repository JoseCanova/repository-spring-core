package org.nanotek.repository.jpa.projections;
import java.io.Serializable;
import java.util.Optional;

import org.nanotek.entities.MutableGidEntity;

public interface GidBaseProjection<K extends MutableGidEntity<TID>, TID extends Serializable> {
   Optional<K> findByGid(TID id);
}
