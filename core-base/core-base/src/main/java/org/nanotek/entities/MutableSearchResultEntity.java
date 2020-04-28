package org.nanotek.entities;

import org.nanotek.entities.immutables.SearchResultEntity;

public interface MutableSearchResultEntity<K> extends SearchResultEntity<K>{
void setSearchResult(K k);
}
