package org.nanotek.entities;

import java.util.Set;

import org.nanotek.beans.entity.search.SearchResult;
import org.nanotek.entities.immutables.SetSearchResultsEntity;

public interface MutableSetSearchResultsEntity extends SetSearchResultsEntity{

	void setSearchResults(Set<SearchResult<?>> searchResults);
	
}
