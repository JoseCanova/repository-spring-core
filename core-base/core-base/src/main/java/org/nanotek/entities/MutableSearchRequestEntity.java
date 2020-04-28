package org.nanotek.entities;

import org.nanotek.entities.immutables.SearchRequestEntity;

public interface MutableSearchRequestEntity<K> 
extends SearchRequestEntity<K>{
void setSearchRequest(K k);
}
