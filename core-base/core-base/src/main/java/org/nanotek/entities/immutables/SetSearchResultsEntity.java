package org.nanotek.entities.immutables;

import java.util.Set;

import org.nanotek.beans.entity.search.SearchResult;

public interface SetSearchResultsEntity {
Set<SearchResult<?>> getSearchResults();
}
