package org.nanotek.entities;

import org.nanotek.entities.immutables.QueryRequestEntity;

public interface MutableQueryRequestEntity<K> extends QueryRequestEntity<K>{
void setQueryRequest(K k);
}
